package ru.acherm.astonhw2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.acherm.astonhw2.dto.UserDto;
import ru.acherm.astonhw2.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Управление пользователями")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание пользователя",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Пользователь создан")
            })
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "ОК")
            })
    public UserDto get(@PathVariable long id) {
        return userService.get(id);
    }

    @PatchMapping
    @Operation(summary = "Обновление пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь обновлён")
            })
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление пользователя",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Пользователь удалён")
            })
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
