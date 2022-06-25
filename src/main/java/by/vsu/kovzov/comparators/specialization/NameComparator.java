package by.vsu.kovzov.comparators.specialization;

import by.vsu.kovzov.dto.SpecializationDto;

public class NameComparator extends SpecializationDtoComparator{
    @Override
    public int compare(SpecializationDto o1, SpecializationDto o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
