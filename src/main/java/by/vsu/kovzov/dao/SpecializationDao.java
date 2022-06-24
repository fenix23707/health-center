package by.vsu.kovzov.dao;

import by.vsu.kovzov.models.Specialization;

import java.util.List;
import java.util.Optional;

public interface SpecializationDao {
    List<Specialization> findAll();

    Optional<Specialization> findById(Integer id);

    Integer create(Specialization specialization);

    void update(Specialization specialization);

    int delete(Integer specializationId);
}
