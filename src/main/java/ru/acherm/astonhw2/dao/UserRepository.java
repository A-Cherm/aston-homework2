package ru.acherm.astonhw2.dao;

import org.springframework.data.repository.CrudRepository;
import ru.acherm.astonhw2.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
