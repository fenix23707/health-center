package by.vsu.kovzov.utils.pool;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListSet;

public final class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private String jdbcUrl;

    private String user;

    private String password;

    private int maxSize = 1;

    private int validConnectionTimeout;

    private Queue<Connection> freeConnections = new ConcurrentLinkedDeque<>();

    private Set<Connection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public void init(String driverClass, String jdbcUrl, String user,
                     String password, int startSize, int maxSize,
                     int validConnectionTimeout) throws ConnectionPoolException {
        try {
            Driver driver = (Driver) Class.forName(driverClass).
                    getConstructor().newInstance();
            DriverManager.registerDriver(driver);

            destroy();
            this.jdbcUrl = jdbcUrl;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.validConnectionTimeout = validConnectionTimeout;
            for (int i = 0; i < startSize; i++) {
                freeConnections.add(newConnection());
            }
        } catch (InstantiationException | InvocationTargetException
                | NoSuchMethodException | IllegalAccessException
                | ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public synchronized Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        while (connection == null) {
            try {
                connection = freeConnections.poll();
                if (connection != null) {
                    if (!connection.isValid(validConnectionTimeout)) {
                        close(connection);
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = newConnection();
                } else {
                    throw new ConnectionPoolException("database connection" +
                            " limit reached");
                }
            } catch (SQLException ex) {
                throw new ConnectionPoolException(ex);
            }
        }
        usedConnections.add(connection);
        return connection;
    }

    void freeConnection(Connection connection) {
        try {
            connection.clearWarnings();
            if (usedConnections.remove(connection)) {
                freeConnections.add(connection);
            }
        } catch (SQLException ex) {
            close(connection);
        }
    }

    @SneakyThrows
    private Connection newConnection() {
        return new ConnectionWrapper(DriverManager.getConnection(jdbcUrl, user, password));
    }

    public void destroy() {
        synchronized (freeConnections) {
            synchronized (usedConnections) {
                usedConnections.addAll(freeConnections);
                freeConnections.clear();
                for (Connection connection : usedConnections) {
                    close(connection);
                }
                usedConnections.clear();
            }
        }
    }

    private void close(Connection connection) {
        synchronized (connection) {
            try {
                connection.rollback();
            } catch (Exception e) {}
            try {
                ((ConnectionWrapper) connection).getConnection().close();
            } catch (Exception e) {}
        }
    }

}
