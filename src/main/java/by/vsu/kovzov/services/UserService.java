package by.vsu.kovzov.services;

import by.vsu.kovzov.models.ListConfig;
import by.vsu.kovzov.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll(ListConfig listConfig);

    User findById(Long id);

    void save(User user);

    boolean delete(Long id);
}
