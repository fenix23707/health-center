package by.vsu.kovzov.controllers.commands.doctor;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Doctor;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.SpecializationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class DoctorEditCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        try {
            Integer specializationId = Integer.valueOf(req.getParameter("specializationId"));
            Specialization specialization = getServiceFactory().getSpecializationService().findById(specializationId)
                    .orElseThrow(() -> new RuntimeException());
            req.setAttribute("specialization", specialization);
        } catch (Exception e) {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));
            params.put("message", "can't find specialization");
            return new CommandResult("/doctor/list.html", CommandResult.Type.FORWARD, params);
        }
        if (id != null) {
            DoctorService doctorService = getServiceFactory().getDoctorService();
            Long finalId = id;
            Doctor doctor = doctorService.getById(id)
                    .orElseThrow(() -> new RuntimeException("can't find doctor with id = " + finalId));
            req.setAttribute("doctor", doctor);
        }
        return null;
    }
}
