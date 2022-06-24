package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Doctor;

import java.math.BigDecimal;

public interface SalaryService {
    BigDecimal calculateSalary(Doctor doctor);
}
