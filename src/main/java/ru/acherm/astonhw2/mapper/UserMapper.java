package ru.acherm.astonhw2.mapper;

import org.springframework.stereotype.Component;
import ru.acherm.astonhw2.dto.UserDto;
import ru.acherm.astonhw2.model.User;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getCreatedAt()
        );
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.getName(),
                userDto.getEmail(),
                userDto.getAge()
        );
    }
}
