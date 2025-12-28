package ru.acherm.astonhw2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.acherm.astonhw2.dao.UserRepository;
import ru.acherm.astonhw2.dto.UserDto;
import ru.acherm.astonhw2.event.UserCreatedEvent;
import ru.acherm.astonhw2.event.UserDeletedEvent;
import ru.acherm.astonhw2.event.UserEvent;
import ru.acherm.astonhw2.mapper.UserMapper;
import ru.acherm.astonhw2.model.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public UserDto create(UserDto user) {
        UserDto createdDto = userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
        UserCreatedEvent createdEvent = userMapper.toCreatedEvent(createdDto);

        kafkaTemplate.send("user-created-event-topic",
                createdEvent.getId().toString(), createdEvent);
        return createdDto;
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
        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isPresent()) {
            UserDeletedEvent deletedEvent = userMapper.toDeletedEvent(optUser.get());
            userRepository.deleteById(id);
            kafkaTemplate.send("user-deleted-event-topic",
                    deletedEvent.getId().toString(), deletedEvent);
        }
    }
}
