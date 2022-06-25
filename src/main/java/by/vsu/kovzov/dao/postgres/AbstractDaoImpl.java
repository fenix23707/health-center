package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.models.SortConfig;

import java.sql.Connection;

import static java.lang.String.format;

public abstract class AbstractDaoImpl {

    private Connection connection;

    protected Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected String addSort(String sql, SortConfig sortConfig) {
        String result = sql;
        if (sortConfig != null) {
            result += format(" ORDER BY %s %s", sortConfig.getColumn(), sortConfig.getOrder());
        }
        return result;
    }

    protected void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {}
    }
}
