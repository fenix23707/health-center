package by.vsu.kovzov.dao;

import by.vsu.kovzov.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findById(Long userId);
}
