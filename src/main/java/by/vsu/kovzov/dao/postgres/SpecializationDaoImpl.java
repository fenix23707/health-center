package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.models.Specialization;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecializationDaoImpl extends AbstractDaoImpl implements SpecializationDao {

    @Override
    @SneakyThrows
    public List<Specialization> findAll() {
        String sql = "SELECT id, name, wage_rate, narrow FROM specializations";
        List<Specialization> specializations = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Specialization specialization = Specialization.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .wageRate(resultSet.getBigDecimal("wage_rate"))
                        .narrow(resultSet.getBoolean("narrow"))
                        .build();
                specializations.add(specialization);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return specializations;
    }

    @Override
    @SneakyThrows
    public Optional<Specialization> findById(Integer id) {
        String sql = "SELECT name, wage_rate, narrow FROM specializations WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<Specialization> optionalSpecialization = Optional.empty();
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Specialization specialization = Specialization.builder()
                        .id(id)
                        .name(resultSet.getString("name"))
                        .wageRate(resultSet.getBigDecimal("wage_rate"))
                        .narrow(resultSet.getBoolean("narrow"))
                        .build();
                optionalSpecialization = Optional.of(specialization);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return optionalSpecialization;
    }

    @Override
    @SneakyThrows
    public Integer create(Specialization specialization) {
        String sql = "INSERT INTO specializations (name, wage_rate, narrow) VALUES (?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, specialization.getName());
            statement.setBigDecimal(2, specialization.getWageRate());
            statement.setBoolean(3, specialization.isNarrow());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            Integer id = resultSet.getInt(1);
            specialization.setId(id);
            return id;
        } finally {
            close(statement);
            close(resultSet);
        }
    }

    @Override
    @SneakyThrows
    public void update(Specialization specialization) {
        String sql = "UPDATE specializations SET name = ?, wage_rate = ?, narrow = ? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, specialization.getName());
            statement.setBigDecimal(2, specialization.getWageRate());
            statement.setBoolean(3, specialization.isNarrow());
            statement.setInt(4, specialization.getId());
            statement.executeUpdate();
        } finally {
            close(statement);
        }
    }

    @Override
    @SneakyThrows
    public int delete(Integer specializationId) {
        String sql = "DELETE FROM specializations WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, specializationId);
            return statement.executeUpdate();
        } finally {
            close(statement);
        }
    }
}
