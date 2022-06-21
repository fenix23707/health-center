package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.dao.postgres.UserDaoImpl;
import by.vsu.kovzov.services.Transaction;
import by.vsu.kovzov.services.UserService;
import by.vsu.kovzov.services.impl.TransactionImpl;
import by.vsu.kovzov.services.impl.UserServiceImpl;
import by.vsu.kovzov.utils.pool.ConnectionPool;
import lombok.SneakyThrows;

import java.sql.Connection;

public class ServiceFactoryImpl implements ServiceFactory{

    private Connection connection = null;
    private Transaction transaction = null;

    private UserDao userDao = null;

    private UserService userService = null;

    @Override
    public UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl(getUserDao());
        }

        return userService;
    }

    protected UserDao getUserDao() {
        if (userDao == null) {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDaoImpl.setConnection(getConnection());
            userDao = userDaoImpl;
        }

        return userDao;
    }

    protected Transaction getTransaction() {
        if (transaction == null) {
            transaction = new TransactionImpl(getConnection());
        }

        return transaction;
    }

    @SneakyThrows
    protected Connection getConnection() {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }

        return connection;
    }

    @Override
    public void close() throws Exception {
        try {
            this.connection.close();
        } catch (Exception e) {}
    }
}
