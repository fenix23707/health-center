package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Doctor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<Doctor> getAll();

    List<Doctor> getAllBySpecialization(Integer specializationId);

    Optional<Doctor> getById(Long id);

    int getDoctorsNumberBySpecialization(Integer specializationId);

    BigDecimal getTotalSalaryBySpecialization(Integer specializationId);

    void save(Doctor doctor);
}
