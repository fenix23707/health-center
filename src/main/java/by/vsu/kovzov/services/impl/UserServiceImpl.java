package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.models.ListConfig;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.UserService;
import by.vsu.kovzov.services.exceptions.ServiceException;
import by.vsu.kovzov.services.transaction.Transaction;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl extends AbstractService implements UserService {

    private final UserDao userDao;

    @Override
    @Transaction
    public List<User> getAll(ListConfig listConfig) {
        return userDao.findAll(listConfig);
    }

    @Override
    public User findById(Long userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        return optionalUser
                .orElseThrow(() -> new ServiceException(HttpStatus.SC_NOT_FOUND, "can't find user with id = " + userId));
    }

    @Override
    public void save(User user) {
        checkUniqueLogin(user);
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

    private void checkUniqueLogin(User user) {
        Optional<User> optional = userDao.findByLogin(user.getLogin());
        if (optional.isPresent() && !optional.get().getId().equals(user.getId())) {
            throw new ServiceException(HttpStatus.SC_CONFLICT, "user with login = " + user.getLogin() + " already exist");
        }
    }
}
