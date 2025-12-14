package ru.acherm.astonhw2.dao;

import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import ru.acherm.astonhw2.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class UserDaoImplTest {
    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16.1")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("postgres");

    private static Configuration config;

    private UserDao userDao;

    @BeforeAll
    static void init() {
        config = new Configuration().configure();
        config.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
    }

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl(config.buildSessionFactory());
    }

    @Test
    void shouldCreateUser() {
        User newUser = new User("user", "user@ya.ru", 20);

        User created = userDao.create(newUser);

        assertThat(created.getName()).isEqualTo(newUser.getName());
        assertThat(created.getId()).isNotNull();
    }

    @Test
    void shouldReturnFoundUser() {
        User newUser = new User("user2", "user2@ya.ru", 20);
        User created = userDao.create(newUser);

        User found = userDao.get(created.getId());

        assertThat(found.getName()).isEqualTo(created.getName());
        assertThat(found.getEmail()).isEqualTo(created.getEmail());
    }

    @Test
    void shouldReturnUpdatedUser() {
        User newUser = new User("user3", "user3@ya.ru", 20);
        User created = userDao.create(newUser);

        created.setName("updatedName");
        User updated = userDao.update(created);

        assertThat(updated.getName()).isEqualTo(created.getName());
    }

    @Test
    void shouldDeleteUser() {
        User newUser = new User("user4", "user4@ya.ru", 20);
        User created = userDao.create(newUser);
        long id = created.getId();

        userDao.delete(created.getId());
        User found = userDao.get(id);

        assertThat(found).isNull();
    }
}