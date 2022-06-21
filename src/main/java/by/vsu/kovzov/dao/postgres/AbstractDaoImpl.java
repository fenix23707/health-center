package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.pool.ConnectionPool;
import lombok.SneakyThrows;

import java.sql.Connection;

public abstract class AbstractDaoImpl {

    @SneakyThrows
    protected Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    protected void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {}
    }
}
