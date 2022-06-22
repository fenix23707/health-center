package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.SpecializationService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SpecializationServiceImpl extends AbstractService implements SpecializationService {

    private SpecializationDao specializationDao;

    @Override
    public List<Specialization> getAll() {
        return specializationDao.findAll();
    }
}
