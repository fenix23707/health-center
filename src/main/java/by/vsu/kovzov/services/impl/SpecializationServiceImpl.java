package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.dto.SpecializationDto;
import by.vsu.kovzov.models.ListConfig;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.SpecializationService;
import by.vsu.kovzov.services.exceptions.ServiceException;
import by.vsu.kovzov.services.transaction.Transaction;
import lombok.Setter;
import org.apache.http.HttpStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
public class SpecializationServiceImpl extends AbstractService implements SpecializationService {

    private SpecializationDao specializationDao;

    private DoctorService doctorService;

    @Override
    @Transaction
    public List<SpecializationDto> getAll(ListConfig config) {
        List<SpecializationDto> specializations = specializationDao.findAll().stream()
                .map(specialization -> SpecializationDto.builder()
                        .id(specialization.getId())
                        .name(specialization.getName())
                        .narrow(specialization.isNarrow())
                        .wageRate(specialization.getWageRate())
                        .doctorsNumber(doctorService.getDoctorsNumberBySpecialization(specialization.getId()))
                        .totalCost(doctorService.getTotalSalaryBySpecialization(specialization.getId()))
                        .build()
                ).collect(Collectors.toList());

        if (config.getSortConfig() != null) {
            String field = config.getSortConfig().getColumn();
            Comparator comparator = getComparatorFactory().getSpecializationDtoComparator(field);
            sort(specializations, comparator, config.getSortConfig());
        }
        return specializations;
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
            checkCanChangeNarrow(specialization);
            specializationDao.update(specialization);
        }
    }

    @Override
    public boolean delete(Integer specializationId) {
        checkCanDelete(specializationId);
        return specializationDao.delete(specializationId) == 1;
    }

    private void checkCanChangeNarrow(Specialization specialization) {
        Specialization old = specializationDao.findById(specialization.getId())
                .orElseThrow(() -> new ServiceException(HttpStatus.SC_BAD_REQUEST, "???? ???????? ?????????? ?????????????????????????? ?? id = " + specialization.getId()));
        if (doctorService.getDoctorsNumberBySpecialization(specialization.getId()) > 0 && old.isNarrow() != specialization.isNarrow()) {
            throw new ServiceException(HttpStatus.SC_CONFLICT, "???????????????????? ?????????? ?? ???????? ????????????????????????????");
        }
    }

    private void checkCanDelete(Integer specializationId) {
        if (doctorService.getDoctorsNumberBySpecialization(specializationId) > 0) {
            throw new ServiceException(HttpStatus.SC_CONFLICT, "???????????????????? ?????????? ?? ???????? ????????????????????????????");
        }
    }
}
