package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Config;
import by.vsu.kovzov.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll(Config config);

    User findById(Long id);

    void save(User user);

    boolean delete(Long id);
}
