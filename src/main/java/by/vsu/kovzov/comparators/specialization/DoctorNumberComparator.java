package by.vsu.kovzov.comparators.specialization;

import by.vsu.kovzov.dto.SpecializationDto;

public class DoctorNumberComparator extends SpecializationDtoComparator{

    @Override
    public int compare(SpecializationDto o1, SpecializationDto o2) {
        return Integer.compare(o1.getDoctorsNumber(), o2.getDoctorsNumber());
    }
}
