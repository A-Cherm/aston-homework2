package ru.acherm.astonhw2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.acherm.astonhw2.dao.UserDao;
import ru.acherm.astonhw2.dao.UserDaoImpl;
import ru.acherm.astonhw2.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    private UserService userService;
    private UserDao userDao;

    @BeforeEach
    void setup() {
        userDao = Mockito.mock(UserDaoImpl.class);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    void shouldReturnCreatedUser() {
        User newUser = new User("user", "user@ya.ru", 20);
        User created = new User("user", "user@ya.ru", 20);
        when(userDao.create(newUser)).thenReturn(created);

        User result = userService.create(newUser);

        verify(userDao, times(1)).create(newUser);
        assertThat(result).isEqualTo(created);
    }

    @Test
    void shouldReturnFoundUser() {
        User user = new User("user", "user@ya.ru", 20);
        when(userDao.get(1L)).thenReturn(user);

        User result = userService.get(1L);

        verify(userDao, times(1)).get(1L);
        assertThat(result).isEqualTo(user);
    }

    @Test
    void shouldReturnUpdatedUser() {
        User user = new User("user", "user@ya.ru", 20);
        User updated = new User("user", "user@ya.ru", 20);
        when(userDao.update(user)).thenReturn(updated);

        User result = userService.update(user);

        verify(userDao, times(1)).update(user);
        assertThat(result).isEqualTo(updated);
    }

    @Test
    void shouldDeleteUser() {
        userService.delete(1L);

        verify(userDao, times(1)).delete(1L);
    }
}