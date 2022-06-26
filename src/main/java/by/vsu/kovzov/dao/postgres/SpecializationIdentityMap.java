package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.models.Specialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SpecializationIdentityMap implements SpecializationDao {

    private static final Map<Integer, Specialization> hash = new ConcurrentHashMap<>();

    private SpecializationDao specializationDao;

    public SpecializationIdentityMap(SpecializationDao specializationDao) {
        this.specializationDao = specializationDao;
    }

    @Override
    public List<Specialization> findAll() {
        return specializationDao.findAll();
    }

    @Override
    public Optional<Specialization> findById(Integer id) {
        Optional<Specialization> specialization;
        if (hash.containsKey(id)) {
            specialization = Optional.of(hash.get(id));
        } else {
            specialization = specializationDao.findById(id);
            if (specialization.isPresent()) {
                hash.put(id, specialization.get());
            }
        }
        return specialization;
    }

    @Override
    public Integer create(Specialization specialization) {
        Integer id = specializationDao.create(specialization);
        hash.put(id, specialization);
        return id;
    }

    @Override
    public void update(Specialization specialization) {
        hash.put(specialization.getId(), specialization);
        specializationDao.update(specialization);
    }

    @Override
    public int delete(Integer specializationId) {
        hash.remove(specializationId);
        return specializationDao.delete(specializationId);
    }
}
