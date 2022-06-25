package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.comparators.specialization.SpecializationDtoComparator;

public interface ComparatorFactory {
    SpecializationDtoComparator getSpecializationDtoComparator(String field);
}
