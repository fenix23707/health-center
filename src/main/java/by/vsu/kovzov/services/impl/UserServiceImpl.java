package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.models.Config;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.UserService;
import by.vsu.kovzov.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl extends AbstractService implements UserService {

    private final UserDao userDao;

    @Override
    public List<User> getAll(Config config) {
        return userDao.findAll(config);
    }

    @Override
    public User findById(Long userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        return optionalUser
                .orElseThrow(() -> new ServiceException(HttpStatus.SC_NOT_FOUND, "can't find user with id = " + userId));
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            userDao.create(user);
        } else {
            userDao.update(user);
        }
    }

    @Override
    public boolean delete(Long userId) {
        return userDao.delete(userId) == 1;
    }
}
