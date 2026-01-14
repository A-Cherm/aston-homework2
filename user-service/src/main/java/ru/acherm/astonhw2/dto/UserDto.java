package ru.acherm.astonhw2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Сущность пользователя")
public class UserDto {
    @Schema(description = "Id пользователя", example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", example = "Bob")
    private String name;
    @Schema(description = "Почта пользователя", example = "Bob@mail.com")
    private String email;
    @Schema(description = "Возраст пользователя", example = "33")
    private Integer age;
    @Schema(description = "Дата и время создания пользователя", example = "2000-01-01T00:00:00",
            accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    public UserDto() {
    }

    public UserDto(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserDto(Long id, String name, String email, Integer age, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
