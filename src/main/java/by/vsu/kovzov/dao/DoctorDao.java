package by.vsu.kovzov.dao;

import by.vsu.kovzov.models.Doctor;

import java.math.BigDecimal;
import java.util.List;

public interface DoctorDao {
    int countBySpecialization(Integer specializationId);

    BigDecimal sumSalaryBySpecialization(Integer specializationId);

    List<Doctor> findAll();

    List<Doctor> findAllBySpecialization(Integer specializationId);
}
