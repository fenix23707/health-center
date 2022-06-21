package by.vsu.kovzov;

import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.dao.postgres.UserDaoImpl;
import by.vsu.kovzov.utils.factories.ServiceFactory;
import by.vsu.kovzov.utils.factories.ServiceFactoryImpl;
import by.vsu.kovzov.utils.pool.ConnectionPool;
import lombok.SneakyThrows;

public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        init();

        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        System.out.println(serviceFactory.getUserService().getAll());
        serviceFactory.close();

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
