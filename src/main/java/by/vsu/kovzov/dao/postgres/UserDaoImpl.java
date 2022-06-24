package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.models.User;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
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
    public Optional<User> findById(Long userId) {
        String sql = "SELECT login, role FROM users WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<User> optionalUser = Optional.empty();
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(userId);
                user.setLogin(resultSet.getString("login"));
                user.setRole(User.Role.values()[resultSet.getInt("role")]);
                optionalUser = Optional.of(user);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return optionalUser;
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

    @Override
    @SneakyThrows
    public Long create(User user) {
        String sql = "INSERT INTO users (login, password, role) VALUES (?,?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(1);
            user.setId(id);
            return id;
        } finally {
            close(statement);
            close(resultSet);
        }
    }

    @Override
    @SneakyThrows
    public void update(User user) {
        String sql = "UPDATE users SET login = ?, password = coalesce(? , password), role = ? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getLogin());
            if (user.getPassword() == null || user.getPassword().isBlank()) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, user.getPassword());
            }
            statement.setInt(3, user.getRole().ordinal());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } finally {
            close(statement);
        }
    }

    @Override
    @SneakyThrows
    public int delete(Long userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, userId);
            return statement.executeUpdate();
        } finally {
            close(statement);
        }
    }
}
