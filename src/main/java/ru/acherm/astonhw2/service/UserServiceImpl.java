package ru.acherm.astonhw2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.acherm.astonhw2.dao.UserRepository;
import ru.acherm.astonhw2.dto.UserDto;
import ru.acherm.astonhw2.mapper.UserMapper;
import ru.acherm.astonhw2.model.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto create(UserDto user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public UserDto get(long id) {
        Optional<User> optUser = userRepository.findById(id);
        return optUser.map(userMapper::toDto).orElse(null);
    }

    @Override
    public UserDto update(UserDto user) {
        Optional<User> optUser = userRepository.findById(user.getId());

        if (optUser.isEmpty()) return null;
        User oldUser = optUser.get();

        if (user.getName() != null && !user.getName().isBlank())
            oldUser.setName(user.getName());
        if (user.getEmail() != null && !user.getEmail().isBlank())
            oldUser.setEmail(user.getEmail());
        if (user.getAge() != null && user.getAge() > 0)
            oldUser.setAge(user.getAge());

        return userMapper.toDto(userRepository.save(oldUser));
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
