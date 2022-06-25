package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.DoctorDao;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.EmployeeService;
import by.vsu.kovzov.services.SalaryService;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Setter
public class DoctorServiceImpl extends AbstractService implements DoctorService {

    private DoctorDao doctorDao;

    private SalaryService salaryService;

    private EmployeeService employeeService;

    @Override
    public List<Doctor> getAll() {
        return doctorDao.findAll();
    }

    @Override
    public List<Doctor> getAllBySpecialization(Integer specializationId) {
        return doctorDao.findAllBySpecialization(specializationId);
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return doctorDao.findById(id);
    }

    @Override
    public void save(Doctor doctor) {
        employeeService.checkEmploymentAge(doctor);
        doctor.setSalary(salaryService.calculateSalary(doctor));

        if (doctor.getId() == null) {
            doctorDao.create(doctor);
        } else {
            doctorDao.update(doctor);
        }
    }

    @Override
    public boolean delete(Long id) {
        return doctorDao.delete(id) == 1;
    }

    @Override
    public int getDoctorsNumberBySpecialization(Integer specializationId) {
        return doctorDao.countBySpecialization(specializationId);
    }

    @Override
    public BigDecimal getTotalSalaryBySpecialization(Integer specializationId) {
        BigDecimal totalSalary = doctorDao.sumSalaryBySpecialization(specializationId);
        if (totalSalary == null) {
            totalSalary = BigDecimal.ZERO;
        }
        return totalSalary;
    }
}
