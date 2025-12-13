package ru.acherm.astonhw2.service;

import ru.acherm.astonhw2.dao.UserDao;
import ru.acherm.astonhw2.model.User;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }
}
