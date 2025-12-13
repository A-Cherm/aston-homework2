package ru.acherm.astonhw2;

import ru.acherm.astonhw2.dao.UserDaoImpl;
import ru.acherm.astonhw2.model.User;
import ru.acherm.astonhw2.service.UserService;
import ru.acherm.astonhw2.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(new UserDaoImpl());
        System.out.println("Старт");

        User user1 = new User("user1", "a@mail", 20);
        User user2 = new User("user2", "b@mail", 30);
        User user3 = new User("user3", "b@mail", 40);

        System.out.println("Создание пользователей");
        System.out.println(userService.create(user1));
        System.out.println(userService.create(user2));
        System.out.println(userService.create(user3));
        System.out.println(user1);

        System.out.println("Получение пользователей");
        System.out.println(userService.get(user1.getId()));
        System.out.println(userService.get(user2.getId()));

        System.out.println("Обновление пользователей");
        user1.setName("new name");
        System.out.println(userService.update(user1));

        System.out.println("Удаление пользователей");
        userService.delete(user1.getId());
        userService.delete(user2.getId());
        System.out.println(user1);
        System.out.println(userService.get(user1.getId()));
    }
}