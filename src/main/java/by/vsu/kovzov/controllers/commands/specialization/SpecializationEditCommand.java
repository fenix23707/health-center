package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.SpecializationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpecializationEditCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            SpecializationService service = getServiceFactory().getSpecializationService();
            Integer finalId = id;
            Specialization specialization = service.findById(id)
                    .orElseThrow(() -> new RuntimeException("can't find specialization with id = " + finalId));
            req.setAttribute("specialization", specialization);
        }
        return null;
    }
}
