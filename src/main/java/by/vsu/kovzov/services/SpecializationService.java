package by.vsu.kovzov.services;

import by.vsu.kovzov.dto.SpecializationDto;
import by.vsu.kovzov.models.ListConfig;
import by.vsu.kovzov.models.Specialization;

import java.util.List;
import java.util.Optional;

public interface SpecializationService {

    List<SpecializationDto> getAll(ListConfig listConfig);

    Optional<Specialization> findById(Integer id);

    void save(Specialization specialization);

    boolean delete(Integer id);
}
