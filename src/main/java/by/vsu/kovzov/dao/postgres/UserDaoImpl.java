package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.models.User;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    @Override
    @SneakyThrows
    public List<User> findAll() {
        String sql = "SELECT id, login, role FROM users";
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
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
            close(connection);
        }
        return users;
    }
}
