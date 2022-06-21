package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.services.Transaction;

public class AbstractService {

    private Transaction transaction;

    protected Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
