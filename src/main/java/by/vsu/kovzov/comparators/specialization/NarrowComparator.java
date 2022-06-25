package by.vsu.kovzov.comparators.specialization;

import by.vsu.kovzov.dto.SpecializationDto;

public class NarrowComparator extends SpecializationDtoComparator {

    @Override
    public int compare(SpecializationDto o1, SpecializationDto o2) {
        return Boolean.compare(o1.isNarrow(), o2.isNarrow());
    }
}
