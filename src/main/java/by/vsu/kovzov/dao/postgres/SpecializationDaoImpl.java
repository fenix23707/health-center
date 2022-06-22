package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.models.User;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
