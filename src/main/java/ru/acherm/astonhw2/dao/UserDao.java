package ru.acherm.astonhw2.dao;

import ru.acherm.astonhw2.model.User;

public interface UserDao {
    User create(User user);

    User get(long id);

    User update(User user);

    void delete(long id);
}
