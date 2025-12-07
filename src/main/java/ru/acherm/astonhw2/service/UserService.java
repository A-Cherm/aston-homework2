package ru.acherm.astonhw2.service;

import ru.acherm.astonhw2.model.User;

public interface UserService {
    User create(User user);

    User get(long id);

    User update(User user);

    void delete(long id);
}
