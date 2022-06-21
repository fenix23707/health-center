package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.UserService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }
}
