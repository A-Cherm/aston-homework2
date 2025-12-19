package ru.acherm.astonhw2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.acherm.astonhw2.dao.UserRepository;
import ru.acherm.astonhw2.dto.UserDto;
import ru.acherm.astonhw2.mapper.UserMapper;
import ru.acherm.astonhw2.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldReturnCreatedUser() {
        UserDto newUser = new UserDto("user", "user@ya.ru", 20);
        User created = new User("user", "user@ya.ru", 20);
        when(userMapper.toDto(any())).thenReturn(newUser);

        UserDto result = userService.create(newUser);

        verify(userRepository, times(1)).save(any());
        assertThat(result.getName()).isEqualTo(created.getName());
        assertThat(result.getEmail()).isEqualTo(created.getEmail());
        assertThat(result.getAge()).isEqualTo(created.getAge());
    }

    @Test
    void shouldReturnFoundUser() {
        User user = new User("user", "user@ya.ru", 20);
        UserDto userDto = new UserDto("user", "user@ya.ru", 20);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(any())).thenReturn(userDto);

        UserDto result = userService.get(1L);

        verify(userRepository, times(1)).findById(1L);
        assertThat(result.getName()).isEqualTo(user.getName());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
        assertThat(result.getAge()).isEqualTo(user.getAge());
    }

    @Test
    void shouldReturnUpdatedUser() {
        User user = new User("user", "user@ya.ru", 20);
        user.setId(1L);
        UserDto userDto = new UserDto(1L, "user", "user@ya.ru", 20, null);
        User updated = new User("new user", "newuser@ya.ru", 20);
        UserDto updatedDto = new UserDto("new user", "newuser@ya.ru", 20);
        when(userRepository.findById(userDto.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(updatedDto);

        UserDto result = userService.update(userDto);

        verify(userRepository, times(1)).save(any());
        assertThat(result.getName()).isEqualTo(updated.getName());
        assertThat(result.getEmail()).isEqualTo(updated.getEmail());
        assertThat(result.getAge()).isEqualTo(updated.getAge());
    }

    @Test
    void shouldDeleteUser() {
        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}