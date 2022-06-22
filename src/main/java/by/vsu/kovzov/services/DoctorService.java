package by.vsu.kovzov.services;

import java.math.BigDecimal;

public interface DoctorService {
    int getDoctorsNumberBySpecialization(Integer specializationId);

    BigDecimal getTotalSalaryBySpecialization(Integer specializationId);
}
