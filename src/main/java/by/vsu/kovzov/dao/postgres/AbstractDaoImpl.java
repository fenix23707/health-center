package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.pool.ConnectionPool;
import lombok.SneakyThrows;

import java.sql.Connection;

public abstract class AbstractDaoImpl {

    private Connection connection;

    protected Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {}
    }
}
