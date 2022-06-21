package by.vsu.kovzov;

import by.vsu.kovzov.pool.ConnectionPool;
import lombok.SneakyThrows;

import java.sql.Connection;

public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        init();
        System.out.println("init");
        Connection connection = ConnectionPool.getInstance().getConnection();
        System.out.println("connected");
        connection.close();
        System.out.println("closed");
        destroy();
        System.out.println("destroyed");
    }

    @SneakyThrows
    private static void init() {
        ConnectionPool.getInstance().init(
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/health_center",
                "postgres",
                "vlad",
                2,
                3,
                1
        );
    }

    private static void destroy() {
        ConnectionPool.getInstance().destroy();
    }
}
