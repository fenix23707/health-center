package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.EmployeeService;
import by.vsu.kovzov.services.SalaryService;
import by.vsu.kovzov.services.SpecializationService;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.NavigableMap;
import java.util.TreeMap;

@Setter
public class SalaryServiceImpl extends AbstractService implements SalaryService {

    private SpecializationService specializationService;

    private EmployeeService employeeService;

    private static final NavigableMap<Integer, Double> SENIORITY_ALLOWANCE = new TreeMap<>();

    static {
        SENIORITY_ALLOWANCE.put(Integer.MIN_VALUE, 0.);
        SENIORITY_ALLOWANCE.put(5, 0.05);
        SENIORITY_ALLOWANCE.put(10, 0.1);
        SENIORITY_ALLOWANCE.put(20, 0.15);
        SENIORITY_ALLOWANCE.put(35, 0.2);
    }

    @Override
    public BigDecimal calculateSalary(Doctor doctor) {
        Specialization specialization = specializationService
                .findById(doctor.getSpecialization().getId())
                .orElseThrow(() -> new RuntimeException("can't calculate salary for doctor " + doctor));
        if (employeeService.isPensioner(doctor)) {
            return specialization.getWageRate().divide(new BigDecimal(2));
        }

        int workExperience = employeeService.calculateYears(doctor.getEmploymentDate());
        double salaryAllowance = SENIORITY_ALLOWANCE.lowerEntry(workExperience).getValue();
        return specialization.getWageRate().multiply(new BigDecimal(salaryAllowance + 1));
    }


}
