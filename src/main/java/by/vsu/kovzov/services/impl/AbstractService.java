package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.models.SortConfig;
import by.vsu.kovzov.services.Transaction;
import by.vsu.kovzov.services.exceptions.ServiceException;
import by.vsu.kovzov.utils.factories.ComparatorFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.apache.http.HttpStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class AbstractService {

    private Transaction transaction;

    private ComparatorFactory comparatorFactory;

    protected <T> void sort(List<T> collection, Comparator<T> comparator, SortConfig sortConfig) {
        if (sortConfig == null) {
            return;
        }

        if (comparator == null) {
            throw new ServiceException(HttpStatus.SC_BAD_REQUEST, "can't find by column " + sortConfig.getColumn());
        }

        if (sortConfig.getOrder() == SortConfig.Order.DESC) {
            comparator = comparator.reversed();
        }

        Collections.sort(collection, comparator);
    }
}
