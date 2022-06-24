package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.SpecializationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class SpecializationDeleteCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = null;
        String msg = "Специальность удалена";
        try {
            id = Integer.valueOf(req.getParameter("specializationId"));
        } catch (NumberFormatException e) {
        }
        SpecializationService specializationService = getServiceFactory().getSpecializationService();
        if (id == null || !specializationService.delete(id)) {
            msg = "Не удалось удалить специальность";
        }

        return new CommandResult("/specialization/list.html", CommandResult.Type.REDIRECT, Map.of("message", msg));
    }
}
