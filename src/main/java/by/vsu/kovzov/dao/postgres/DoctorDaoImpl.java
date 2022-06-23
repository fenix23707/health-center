package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.DoctorDao;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.Person;
import by.vsu.kovzov.models.Specialization;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl extends AbstractDaoImpl implements DoctorDao {

    @Override
    @SneakyThrows
    public List<Doctor> findAll() {
        String sql = "SELECT id, name, surname, patronymic, sex, dob, employment_date, salary, specialization_id, branch_id FROM doctors";
        List<Doctor> doctors = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = Doctor.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .sex(Person.Sex.values()[resultSet.getInt("sex")])
                        .dob(resultSet.getDate("dob"))
                        .employmentDate(resultSet.getDate("employment_date"))
                        .salary(resultSet.getBigDecimal("salary"))
                        .specialization(Specialization.builder().id(resultSet.getInt("specialization_id")).build())
                        .branchNo(resultSet.getObject("branch_id", Integer.class))
                        .build();
                doctors.add(doctor);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return doctors;
    }

    @Override
    @SneakyThrows
    public List<Doctor> findAllBySpecialization(Integer specializationId) {
        String sql = "SELECT id, name, surname, patronymic, sex, dob, employment_date, salary, branch_id FROM doctors WHERE specialization_id = ?";
        List<Doctor> doctors = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, specializationId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = Doctor.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .sex(Person.Sex.values()[resultSet.getInt("sex")])
                        .dob(resultSet.getDate("dob"))
                        .employmentDate(resultSet.getDate("employment_date"))
                        .salary(resultSet.getBigDecimal("salary"))
                        .specialization(Specialization.builder().id(specializationId).build())
                        .branchNo(resultSet.getObject("branch_id", Integer.class))
                        .build();
                doctors.add(doctor);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return doctors;
    }

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
