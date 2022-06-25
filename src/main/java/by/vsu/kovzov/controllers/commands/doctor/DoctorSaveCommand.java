package by.vsu.kovzov.controllers.commands.doctor;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.Person;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.ValidateService;
import by.vsu.kovzov.validators.Validator;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Map;

import static by.vsu.kovzov.validators.Validator.POSITIVE;
import static by.vsu.kovzov.validators.Validator.REQUIRED;
import static com.google.common.base.MoreObjects.firstNonNull;
import static java.lang.Integer.getInteger;

public class DoctorSaveCommand extends Command {


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Doctor doctor = getDoctor(req);
        getServiceFactory().getDoctorService().save(doctor);
        return new CommandResult(
                "/doctor/list.html",
                CommandResult.Type.REDIRECT,
                Map.of("specializationId", String.valueOf(doctor.getSpecialization().getId()))
        );
    }

    @SneakyThrows
    private Doctor getDoctor(HttpServletRequest req) {
        ValidateService validate = getServiceFactory().getValidateService();
        return Doctor.builder()
                .id(validate.getLong("id", req))
                .name(validate.getString("name", req, REQUIRED))
                .surname(validate.getString("surname", req, REQUIRED))
                .patronymic(validate.getString("patronymic", req, REQUIRED))
                .sex(Person.Sex.valueOf(req.getParameter("sex")))
                .dob(validate.getDate("dob", req, REQUIRED))
                .employmentDate(validate.getDate("employmentDate", req, REQUIRED))
                .branchNo(validate.getInt("branchNo", req))
                .specialization(Specialization.builder()
                        .id(validate.getInt("specializationId", req, REQUIRED, POSITIVE))
                        .build())
                .build();
    }

}
