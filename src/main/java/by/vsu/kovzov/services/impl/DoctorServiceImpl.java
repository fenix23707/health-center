package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.DoctorDao;
import by.vsu.kovzov.services.DoctorService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class DoctorServiceImpl extends AbstractService implements DoctorService {

    private DoctorDao doctorDao;

    @Override
    public int getDoctorsNumberBySpecialization(Integer specializationId) {
        return doctorDao.countBySpecialization(specializationId);
    }

    @Override
    public BigDecimal getTotalSalaryBySpecialization(Integer specializationId) {
        return doctorDao.sumSalaryBySpecialization(specializationId);
    }
}
