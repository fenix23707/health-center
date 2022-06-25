package by.vsu.kovzov.dao;

import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.ListConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DoctorDao {

    List<Doctor> findAll(ListConfig config);

    List<Doctor> findAllBySpecialization(Integer specializationId, ListConfig config);

    Optional<Doctor> findById(Long id);

    Long create(Doctor doctor);

    void update(Doctor doctor);

    int countBySpecialization(Integer specializationId);

    BigDecimal sumSalaryBySpecialization(Integer specializationId);

    int delete(Long id);
}
