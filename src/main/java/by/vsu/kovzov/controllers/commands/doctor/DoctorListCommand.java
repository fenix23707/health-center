package by.vsu.kovzov.controllers.commands.doctor;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.SpecializationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public class DoctorListCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        List<Doctor> doctors;
        DoctorService doctorService = getServiceFactory().getDoctorService();
        SpecializationService specializationService = getServiceFactory().getSpecializationService();
        if (id != null) {
            doctors = doctorService.getAllBySpecialization(id);
            Optional<Specialization> specialization = specializationService.findById(id);
            if (specialization.isEmpty()) {
                return new CommandResult(
                        "/specialization/list.html",
                        "can't find specialization with id = " + id,
                        CommandResult.Type.FORWARD
                );
            }
            req.setAttribute("specialization", specialization.get());
        } else {
            doctors = doctorService.getAll();
        }
        req.setAttribute("doctors", doctors);
        return null;
    }
}
