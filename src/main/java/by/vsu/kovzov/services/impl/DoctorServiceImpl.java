package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.DoctorDao;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.services.DoctorService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class DoctorServiceImpl extends AbstractService implements DoctorService {

    private final DoctorDao doctorDao;

    @Override
    public List<Doctor> getAll() {
        return doctorDao.findAll();
    }

    @Override
    public List<Doctor> getAllBySpecialization(Integer specializationId) {
        return doctorDao.findAllBySpecialization(specializationId);
    }

    @Override
    public int getDoctorsNumberBySpecialization(Integer specializationId) {
        return doctorDao.countBySpecialization(specializationId);
    }

    @Override
    public BigDecimal getTotalSalaryBySpecialization(Integer specializationId) {
        return doctorDao.sumSalaryBySpecialization(specializationId);
    }

}
