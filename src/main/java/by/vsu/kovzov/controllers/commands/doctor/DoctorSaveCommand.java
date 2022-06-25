package by.vsu.kovzov.controllers.commands.doctor;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.Person;
import by.vsu.kovzov.models.Specialization;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Map;

import static com.google.common.base.MoreObjects.firstNonNull;
import static java.lang.Integer.getInteger;

public class DoctorSaveCommand extends Command {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");

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
        return Doctor.builder()
                .id(Longs.tryParse(firstNonNull(req.getParameter("id"), "")))
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .patronymic(req.getParameter("patronymic"))
                .sex(Person.Sex.valueOf(req.getParameter("sex")))
                .dob(DATE_FORMAT.parse(req.getParameter("dob")))
                .employmentDate(DATE_FORMAT.parse(req.getParameter("employmentDate")))
                .branchNo(getInteger(req.getParameter("branchNo"), null))
                .specialization(Specialization.builder()
                        .id(Ints.tryParse(firstNonNull(req.getParameter("specializationId"), "")))
                        .build())
                .build();
    }

}
