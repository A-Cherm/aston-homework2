package ru.acherm.astonhw2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.acherm.astonhw2.dto.UserDto;
import ru.acherm.astonhw2.service.UserService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {
    private final MockMvc mvc;
    private final ObjectMapper mapper;

    @Autowired
    public UserControllerTest(MockMvc mvc, ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    @MockitoBean
    private UserService userService;

    @Test
    void shouldCreateUser() throws Exception {
        UserDto userDto = new UserDto(null, "user", "user@mail.ru", 20, null);
        UserDto created = new UserDto(1L, "user", "user@mail.ru", 20, LocalDateTime.now());

        when(userService.create(any())).thenReturn(created);

        MvcResult result = mvc.perform(post("/users")
                        .content(mapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        UserDto resultDto = mapper.readValue(json, UserDto.class);

        assertThat(resultDto).isNotNull().usingRecursiveComparison().isEqualTo(created);
    }

    @Test
    void shouldGetUser() throws Exception {
        UserDto userDto = new UserDto(1L, "user", "user@mail.ru", 20, LocalDateTime.now());

        when(userService.get(1L)).thenReturn(userDto);

        MvcResult result = mvc.perform(get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        UserDto resultDto = mapper.readValue(json, UserDto.class);

        assertThat(resultDto).isNotNull().usingRecursiveComparison().isEqualTo(userDto);
    }

    @Test
    void shouldUpdateUser() throws Exception {
        UserDto userDto = new UserDto(1L, "user", "user@mail.ru", 20, null);

        when(userService.update(any())).thenReturn(userDto);

        MvcResult result = mvc.perform(patch("/users")
                        .content(mapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        UserDto resultDto = mapper.readValue(json, UserDto.class);

        assertThat(resultDto).isNotNull().usingRecursiveComparison().isEqualTo(userDto);
    }

    @Test
    void shouldDeleteUser() throws Exception {
        mvc.perform(delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        verify(userService, times(1)).delete(1L);
    }
}