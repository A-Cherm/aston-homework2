package ru.acherm.astonhw2.service;

import ru.acherm.astonhw2.dto.UserDto;

public interface UserService {
    UserDto create(UserDto user);

    UserDto get(long id);

    UserDto update(UserDto user);

    void delete(long id);
}
