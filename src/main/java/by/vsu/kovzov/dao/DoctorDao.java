package by.vsu.kovzov.dao;

import by.vsu.kovzov.models.Doctor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DoctorDao {

    List<Doctor> findAll();

    List<Doctor> findAllBySpecialization(Integer specializationId);

    Optional<Doctor> findById(Long id);

    int countBySpecialization(Integer specializationId);

    BigDecimal sumSalaryBySpecialization(Integer specializationId);
}
