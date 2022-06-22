package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.models.User;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    @Override
    @SneakyThrows
    public List<User> findAll() {
        String sql = "SELECT id, login, role FROM users";
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(User.Role.values()[resultSet.getInt("role")]);
                users.add(user);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return users;
    }

    @Override
    @SneakyThrows
    public Optional<User> findByLoginAndPassword(String login, String password) {
        String sql = "SELECT id, login, role FROM users WHERE login = ? AND password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<User> optionalUser = Optional.empty();
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setRole(User.Role.values()[resultSet.getInt("role")]);
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                optionalUser = Optional.of(user);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return optionalUser;
    }
}
