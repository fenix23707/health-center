package by.vsu.kovzov.services;

public interface Transaction {

    void start();

    void commit();

    void rollback();
}
