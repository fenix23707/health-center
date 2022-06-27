package by.vsu.kovzov.services.transaction;

import by.vsu.kovzov.services.exceptions.TransactionException;
import by.vsu.kovzov.utils.ConnectionStorage;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionHandler implements InvocationHandler {

    private final Object target;
    private final Class<?> targetClass;

    public TransactionHandler(Object target) {
        this.target = target;
        this.targetClass = target.getClass();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method targetMethod = getOverriddenMethod(method);

        return getTransactionMethod(targetMethod)
                .map(annotation -> this.handleTransactionMethod(method, args))
                .orElseGet(() -> this.uncheckedInvoke(method, args));
    }

    @SneakyThrows
    private Object handleTransactionMethod(Method method, Object[] args) {
        Connection connection = ConnectionStorage.INSTANCE.getConnection();
        Object result = null;

        start(connection);
        try {
            result = uncheckedInvoke(method, args);
        } catch (Exception e) {
            rollback(connection);
            throw e;
        }
        commit(connection);

        return result;
    }

    private void start(Connection connection) {
        try {
            System.out.println("open transaction");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    private void commit(Connection connection) {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("close transaction");
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @SneakyThrows
    private Object uncheckedInvoke(Method method, Object[] args) {
        try {
            return method.invoke(target, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.err.println("Could not invoke method " + method.getName());
            throw e.getCause();

//            throw new TransactionException("Could not invoke method " + method.getName(), e);
        }
    }

    private Optional<Transaction> getTransactionMethod(Method method) {
        return Optional.ofNullable(method.getDeclaredAnnotation(Transaction.class));
    }

    private Method getOverriddenMethod(Method method) throws NoSuchMethodException {
        return targetClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
    }
}
