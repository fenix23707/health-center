package by.vsu.kovzov.comparators.specialization;

import by.vsu.kovzov.dto.SpecializationDto;

import java.util.Comparator;

public class WageRateComparator extends SpecializationDtoComparator{

    @Override
    public int compare(SpecializationDto o1, SpecializationDto o2) {
        return o1.getWageRate().compareTo(o2.getWageRate());
    }


}
