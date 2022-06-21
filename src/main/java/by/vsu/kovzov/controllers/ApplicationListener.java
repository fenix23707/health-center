package by.vsu.kovzov.controllers;

import by.vsu.kovzov.utils.pool.ConnectionPool;
import by.vsu.kovzov.utils.pool.ConnectionPoolException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String driverClass = context.getInitParameter("jdbc-driver");
        String jdbcUrl = context.getInitParameter("jdbc-url");
        String user = context.getInitParameter("jdbc-username");
        String password = context.getInitParameter("jdbc-password");
        int startSize = Integer.parseInt(context.getInitParameter("start-size"));
        int maxSize = Integer.parseInt(context.getInitParameter("max-size"));
        int validConnectionTimeout = Integer.parseInt(
                context.getInitParameter("valid-connection-timeout"));

        try {
            ConnectionPool.getInstance().init(driverClass,jdbcUrl,user,password,
                    startSize,maxSize,validConnectionTimeout);

        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroy();
    }
}
