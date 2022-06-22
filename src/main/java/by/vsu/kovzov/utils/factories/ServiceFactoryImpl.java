package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.dao.postgres.SpecializationDaoImpl;
import by.vsu.kovzov.dao.postgres.UserDaoImpl;
import by.vsu.kovzov.services.AuthService;
import by.vsu.kovzov.services.SpecializationService;
import by.vsu.kovzov.services.Transaction;
import by.vsu.kovzov.services.UserService;
import by.vsu.kovzov.services.impl.AuthServiceImpl;
import by.vsu.kovzov.services.impl.SpecializationServiceImpl;
import by.vsu.kovzov.services.impl.TransactionImpl;
import by.vsu.kovzov.services.impl.UserServiceImpl;
import by.vsu.kovzov.utils.pool.ConnectionPool;
import lombok.SneakyThrows;

import java.sql.Connection;

public class ServiceFactoryImpl implements ServiceFactory{

    private Connection connection = null;
    private Transaction transaction = null;

    private UserDao userDao = null;
    private SpecializationDao specializationDao = null;

    private UserService userService = null;
    private AuthService authService = null;
    private SpecializationService specializationService = null;

    @Override
    public UserService getUserService() {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl(getUserDao());
            userServiceImpl.setTransaction(getTransaction());
            userService = userServiceImpl;
        }

        return userService;
    }

    @Override
    public AuthService getAuthService() {
        if (authService == null) {
            AuthServiceImpl authServiceImpl = new AuthServiceImpl(getUserDao());
            authServiceImpl.setTransaction(getTransaction());
            authService = authServiceImpl;
        }

        return authService;
    }

    @Override
    public SpecializationService getSpecializationService() {
        if (specializationService == null) {
            SpecializationServiceImpl specializationServiceImpl = new SpecializationServiceImpl(getSpecializationDao());
            specializationServiceImpl.setTransaction(getTransaction());
            specializationService = specializationServiceImpl;
        }

        return specializationService;
    }

    protected UserDao getUserDao() {
        if (userDao == null) {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDaoImpl.setConnection(getConnection());
            userDao = userDaoImpl;
        }

        return userDao;
    }

    protected SpecializationDao getSpecializationDao() {
        if (specializationDao == null) {
            SpecializationDaoImpl specializationDaoImpl = new SpecializationDaoImpl();
            specializationDaoImpl.setConnection(getConnection());
            specializationDao = specializationDaoImpl;
        }

        return specializationDao;
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
