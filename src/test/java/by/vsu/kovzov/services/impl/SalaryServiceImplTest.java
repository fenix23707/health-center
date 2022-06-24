package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.Person;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.EmployeeService;
import by.vsu.kovzov.services.SpecializationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalaryServiceImplTest {

    private EmployeeService employeeService;

    private SpecializationService specializationService;

    private SalaryServiceImpl salaryService;

    @BeforeEach
    void setUp() {
        this.employeeService = mock(EmployeeService.class);
        this.specializationService = mock(SpecializationService.class);
        salaryService = new SalaryServiceImpl();
        salaryService.setEmployeeService(employeeService);
        salaryService.setSpecializationService(specializationService);
    }

    @Test
    void pensionerCalculateSalaryTest() {
        Specialization specialization = Specialization.builder()
                .id(1)
                .wageRate(new BigDecimal(10))
                .build();
        Doctor doctor = Doctor.builder().specialization(specialization).build();
        BigDecimal expected = specialization.getWageRate().divide(new BigDecimal(2));

        when(specializationService.findById(anyInt())).thenReturn(Optional.of(specialization));
        when(employeeService.isPensioner(any())).thenReturn(true);
        BigDecimal salary = salaryService.calculateSalary(doctor);

        Assertions.assertEquals(expected, salary);
    }


    @Test
    void zeroExperienceCalculateSalaryTest() {
        Specialization specialization = Specialization.builder()
                .id(1)
                .wageRate(new BigDecimal(10))
                .build();
        Doctor doctor = Doctor.builder().specialization(specialization).build();
        BigDecimal expected = specialization.getWageRate();

        when(specializationService.findById(anyInt())).thenReturn(Optional.of(specialization));
        when(employeeService.isPensioner(any())).thenReturn(false);
        when(employeeService.calculateYears(doctor.getEmploymentDate())).thenReturn(0);
        BigDecimal salary = salaryService.calculateSalary(doctor);

        Assertions.assertEquals(expected, salary);
    }

    @Test
    void maxExperienceCalculateSalaryTest() {
        Specialization specialization = Specialization.builder()
                .id(1)
                .wageRate(new BigDecimal(10))
                .build();
        Doctor doctor = Doctor.builder().specialization(specialization).build();
        BigDecimal expected = specialization.getWageRate().multiply(new BigDecimal(1.2));

        when(specializationService.findById(anyInt())).thenReturn(Optional.of(specialization));
        when(employeeService.isPensioner(any())).thenReturn(false);
        when(employeeService.calculateYears(doctor.getEmploymentDate())).thenReturn(100);
        BigDecimal salary = salaryService.calculateSalary(doctor);

        Assertions.assertEquals(expected, salary);
    }

    @Test
    void fiveExperienceCalculateSalaryTest() {
        Specialization specialization = Specialization.builder()
                .id(1)
                .wageRate(new BigDecimal(10))
                .build();
        Doctor doctor = Doctor.builder().specialization(specialization).build();
        BigDecimal expected = specialization.getWageRate();

        when(specializationService.findById(anyInt())).thenReturn(Optional.of(specialization));
        when(employeeService.isPensioner(any())).thenReturn(false);
        when(employeeService.calculateYears(doctor.getEmploymentDate())).thenReturn(5);
        BigDecimal salary = salaryService.calculateSalary(doctor);

        Assertions.assertEquals(expected, salary);
    }

    @Test
    void sixExperienceCalculateSalaryTest() {
        Specialization specialization = Specialization.builder()
                .id(1)
                .wageRate(new BigDecimal(10))
                .build();
        Doctor doctor = Doctor.builder().specialization(specialization).build();
        BigDecimal expected = specialization.getWageRate().multiply(new BigDecimal(1.05));

        when(specializationService.findById(anyInt())).thenReturn(Optional.of(specialization));
        when(employeeService.isPensioner(any())).thenReturn(false);
        when(employeeService.calculateYears(doctor.getEmploymentDate())).thenReturn(6);
        BigDecimal salary = salaryService.calculateSalary(doctor);

        Assertions.assertEquals(expected, salary);
    }
}