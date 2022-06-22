package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.DoctorDao;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DoctorDaoImpl extends AbstractDaoImpl implements DoctorDao {

    @Override
    @SneakyThrows
    public int countBySpecialization(Integer specializationId) {
        String sql = "SELECT count(*) FROM doctors WHERE specialization_id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, specializationId);
            resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    @SneakyThrows
    public BigDecimal sumSalaryBySpecialization(Integer specializationId) {
        String sql = "SELECT sum(salary) FROM doctors WHERE specialization_id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, specializationId);
            resultSet = statement.executeQuery();
            return resultSet.next() ?  resultSet.getBigDecimal(1) : BigDecimal.ZERO;
        } finally {
            close(resultSet);
            close(statement);
        }
    }
}
