package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.comparators.specialization.*;

import java.util.HashMap;
import java.util.Map;

public class ComparatorFactoryImpl implements ComparatorFactory{
    private static final Map<String, SpecializationDtoComparator> SPECIALIZATION_DTO_COMPARATORS = new HashMap<>();

    static {
        SPECIALIZATION_DTO_COMPARATORS.put("name", new NameComparator());
        SPECIALIZATION_DTO_COMPARATORS.put("doctorsNumber", new DoctorNumberComparator());
        SPECIALIZATION_DTO_COMPARATORS.put("wageRate", new WageRateComparator());
        SPECIALIZATION_DTO_COMPARATORS.put("totalCost", new TotalConstComparator());
        SPECIALIZATION_DTO_COMPARATORS.put("narrow", new NarrowComparator());
    }

    @Override
    public SpecializationDtoComparator getSpecializationDtoComparator(String field) {
        return SPECIALIZATION_DTO_COMPARATORS.get(field);
    }
}
