package by.vsu.kovzov.utils;

import java.sql.Connection;

public enum ConnectionStorage implements AutoCloseable {
    INSTANCE;

    private final ThreadLocal<Connection> storage = new ThreadLocal<>();

    public void setConnection(Connection connection) {
        storage.set(connection);
    }

    public Connection getConnection() {
        return storage.get();
    }

    @Override
    public void close() throws Exception {
        storage.get().close();
        storage.remove();
    }
}
