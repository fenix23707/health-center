package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.DoctorDao;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.ListConfig;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.EmployeeService;
import by.vsu.kovzov.services.SalaryService;
import by.vsu.kovzov.services.SpecializationService;
import by.vsu.kovzov.services.exceptions.ServiceException;
import lombok.Setter;
import org.apache.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Setter
public class DoctorServiceImpl extends AbstractService implements DoctorService {

    private DoctorDao doctorDao;

    private SalaryService salaryService;

    private EmployeeService employeeService;

    private SpecializationService specializationService;

    @Override
    public List<Doctor> getAll(ListConfig config) {
        return doctorDao.findAll(config);
    }

    @Override
    public List<Doctor> getAllBySpecialization(Integer specializationId, ListConfig config) {
        return doctorDao.findAllBySpecialization(specializationId, config);
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return doctorDao.findById(id);
    }

    @Override
    public void save(Doctor doctor) {
        employeeService.checkEmploymentAge(doctor);
        checkBranchNo(doctor);

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

    private void checkBranchNo(Doctor doctor) {
        Specialization specialization = specializationService
                .findById(doctor.getSpecialization().getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.SC_NOT_FOUND,
                        "Не получается найти специальность с id = " + doctor.getSpecialization().getId())
                );

        if (!specialization.isNarrow() && doctor.getBranchNo() == null) {
            throw new ServiceException(HttpStatus.SC_BAD_REQUEST,
                    "Врач, которые не является узким специалистом, обязательно должен иметь номер участка");
        }
    }
}
