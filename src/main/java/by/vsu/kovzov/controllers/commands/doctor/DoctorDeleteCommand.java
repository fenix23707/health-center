package by.vsu.kovzov.controllers.commands.doctor;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.services.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class DoctorDeleteCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = null;
        String msg = "Доктор удален";
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        DoctorService doctorService = getServiceFactory().getDoctorService();
        if (id == null || doctorService.delete(id)) {
            msg = "Не удалось удалить доктора";
        }

        return new CommandResult("/doctor/list.html", CommandResult.Type.REDIRECT,
                Map.of("message", msg,
                        "specializationId", req.getParameter("specializationId")
                ));
    }
}
