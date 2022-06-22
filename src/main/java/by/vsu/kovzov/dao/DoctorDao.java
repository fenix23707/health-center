package by.vsu.kovzov.dao;

import java.math.BigDecimal;

public interface DoctorDao {
    int countBySpecialization(Integer specializationId);

    BigDecimal sumSalaryBySpecialization(Integer specializationId);
}
