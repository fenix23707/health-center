package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.services.Transaction;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;

@AllArgsConstructor
public class TransactionImpl implements Transaction {

    private final Connection connection;

    @Override
    @SneakyThrows
    public void start() {
        connection.setAutoCommit(false);
    }

    @Override
    @SneakyThrows
    public void commit() {
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    @SneakyThrows
    public void rollback() {
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
