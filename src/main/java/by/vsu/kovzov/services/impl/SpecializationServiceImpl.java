package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.dto.SpecializationDto;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.SpecializationService;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
public class SpecializationServiceImpl extends AbstractService implements SpecializationService {

    private SpecializationDao specializationDao;

    private DoctorService doctorService;

    @Override
    public List<SpecializationDto> getAll() {
        return specializationDao.findAll().stream()
                .map(specialization -> SpecializationDto.builder()
                        .id(specialization.getId())
                        .name(specialization.getName())
                        .narrow(specialization.isNarrow())
                        .wageRate(specialization.getWageRate())
                        .doctorsNumber(doctorService.getDoctorsNumberBySpecialization(specialization.getId()))
                        .totalCost(doctorService.getTotalSalaryBySpecialization(specialization.getId()))
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<Specialization> findById(Integer id) {
        return specializationDao.findById(id);
    }

    @Override
    public void save(Specialization specialization) {
        if (specialization.getId() == null) {
            specializationDao.create(specialization);
        } else {
            specializationDao.update(specialization);
        }
    }
}
