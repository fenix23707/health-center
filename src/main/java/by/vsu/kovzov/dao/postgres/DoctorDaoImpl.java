package by.vsu.kovzov.dao.postgres;

import by.vsu.kovzov.dao.DoctorDao;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.ListConfig;
import by.vsu.kovzov.models.Person;
import by.vsu.kovzov.models.Specialization;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl extends AbstractDaoImpl implements DoctorDao {

    @Override
    @SneakyThrows
    public List<Doctor> findAll(ListConfig config) {
        String sql = "SELECT id, name, surname, patronymic, sex, dob, employment_date, salary, specialization_id, branch_id FROM doctors";
        sql = addSort(sql, config.getSortConfig());
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
    public List<Doctor> findAllBySpecialization(Integer specializationId, ListConfig config) {
        String sql = "SELECT id, name, surname, patronymic, sex, dob, employment_date, salary, branch_id FROM doctors WHERE specialization_id = ?";
        sql = addSort(sql, config.getSortConfig());
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
    public Optional<Doctor> findById(Long id) {
        String sql = "SELECT name, surname, patronymic, sex, dob, employment_date, salary, specialization_id, branch_id FROM doctors WHERE id = ?";
        Optional<Doctor> doctorOptional = Optional.empty();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Doctor doctor = Doctor.builder()
                        .id(id)
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
                doctorOptional = Optional.of(doctor);
            }
        } finally {
            close(resultSet);
            close(statement);
        }
        return doctorOptional;
    }

    @Override
    @SneakyThrows
    public Long create(Doctor doctor) {
        String sql = "INSERT INTO doctors (name, surname, patronymic, sex, dob, employment_date, salary, specialization_id, branch_id) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            statement.setString(3, doctor.getPatronymic());
            statement.setInt(4, doctor.getSex().ordinal());
            statement.setDate(5, new Date(doctor.getDob().getTime()));
            statement.setDate(6, new Date(doctor.getEmploymentDate().getTime()));
            statement.setBigDecimal(7, doctor.getSalary());
            statement.setInt(8, doctor.getSpecialization().getId());
            if (doctor.getBranchNo() == null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, doctor.getBranchNo());
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(1);
            doctor.setId(id);
            return id;
        } finally {
            close(statement);
            close(resultSet);
        }
    }

    @Override
    @SneakyThrows
    public void update(Doctor doctor) {
        String sql = "UPDATE doctors SET name = ?, surname = ?, patronymic = ?, sex = ?, dob = ?, employment_date = ?, salary = ?, specialization_id = ?, branch_id = ? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            statement.setString(3, doctor.getPatronymic());
            statement.setInt(4, doctor.getSex().ordinal());
            statement.setDate(5, new Date(doctor.getDob().getTime()));
            statement.setDate(6, new Date(doctor.getEmploymentDate().getTime()));
            statement.setBigDecimal(7, doctor.getSalary());
            statement.setInt(8, doctor.getSpecialization().getId());
            if (doctor.getBranchNo() == null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, doctor.getBranchNo());
            }
            statement.setLong(10, doctor.getId());
            statement.executeUpdate();
        } finally {
            close(statement);
        }
    }

    @Override
    @SneakyThrows
    public int delete(Long id) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            return statement.executeUpdate();
        } finally {
            close(statement);
        }
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
            return resultSet.next() ? resultSet.getBigDecimal(1) : BigDecimal.ZERO;
        } finally {
            close(resultSet);
            close(statement);
        }
    }
}
