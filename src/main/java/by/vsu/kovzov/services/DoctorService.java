package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Doctor;

import java.math.BigDecimal;
import java.util.List;

public interface DoctorService {
    int getDoctorsNumberBySpecialization(Integer specializationId);

    BigDecimal getTotalSalaryBySpecialization(Integer specializationId);

    List<Doctor> getAll();

    List<Doctor> getAllBySpecialization(Integer specializationId);
}
