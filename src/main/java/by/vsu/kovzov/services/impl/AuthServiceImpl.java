package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.AuthService;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AuthServiceImpl extends AbstractService implements AuthService {

    private final UserDao userDao;

    @Override
    public Optional<User> signin(String login, String password) {
        return userDao.findByLoginAndPassword(login, password);
    }
}
