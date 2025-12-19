package ru.acherm.astonhw2.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import ru.acherm.astonhw2.model.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
class UserRepositoryTest {
    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:16.1")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("postgres");

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void setPostgres(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
    }

    @Test
    void shouldCreateUser() {
        User newUser = new User("user", "user@ya.ru", 20);

        User created = userRepository.save(newUser);

        assertThat(created.getName()).isEqualTo(newUser.getName());
        assertThat(created.getId()).isNotNull();
    }

    @Test
    void shouldReturnFoundUser() {
        User newUser = new User("user2", "user2@ya.ru", 20);
        User created = userRepository.save(newUser);

        User found = userRepository.findById(created.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(created.getName());
        assertThat(found.getEmail()).isEqualTo(created.getEmail());
    }

    @Test
    void shouldReturnUpdatedUser() {
        User newUser = new User("user3", "user3@ya.ru", 20);
        User created = userRepository.save(newUser);

        created.setName("updatedName");
        User updated = userRepository.save(created);

        assertThat(updated.getName()).isEqualTo(created.getName());
    }

    @Test
    void shouldDeleteUser() {
        User newUser = new User("user4", "user4@ya.ru", 20);
        User created = userRepository.save(newUser);
        long id = created.getId();

        userRepository.deleteById(created.getId());
        User found = userRepository.findById(id).orElse(null);

        assertThat(found).isNull();
    }
}