package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.models.SortConfig;
import by.vsu.kovzov.utils.ConnectionStorage;

import java.sql.Connection;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.String.format;

public abstract class AbstractDaoImpl {

//    private Connection connection;

    protected Connection getConnection() {
        return ConnectionStorage.INSTANCE.getConnection();
    }

    public void setConnection(Connection connection) {
//        this.connection = connection;
    }

    protected String addSort(String sql, SortConfig sortConfig) {
        String result = sql;
        if (sortConfig != null) {
            String order = Arrays.stream(sortConfig.getColumn().split(" "))
                    .filter(s -> !s.isBlank())
                    .map(s -> format("%s %s  NULLS LAST", s, sortConfig.getOrder()))
                    .collect(Collectors.joining(", "));
            result += " ORDER BY " + order;
        }
        return result;
    }

    protected void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }
}
