package by.vsu.kovzov.dao;

import by.vsu.kovzov.models.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
