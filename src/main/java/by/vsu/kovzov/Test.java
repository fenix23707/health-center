package by.vsu.kovzov;

import by.vsu.kovzov.utils.pool.ConnectionPool;
import lombok.SneakyThrows;

public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        init();



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
