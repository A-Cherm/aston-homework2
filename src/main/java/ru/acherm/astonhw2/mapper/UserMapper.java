package ru.acherm.astonhw2.mapper;

import org.springframework.stereotype.Component;
import ru.acherm.astonhw2.dto.UserDto;
import ru.acherm.astonhw2.event.UserCreatedEvent;
import ru.acherm.astonhw2.event.UserDeletedEvent;
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

    public UserCreatedEvent toCreatedEvent(UserDto userDto) {
        return new UserCreatedEvent(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getAge(),
                userDto.getCreatedAt()
        );
    }

    public UserDeletedEvent toDeletedEvent(User user) {
        return new UserDeletedEvent(
                user.getId(),
                user.getEmail()
        );
    }
}
