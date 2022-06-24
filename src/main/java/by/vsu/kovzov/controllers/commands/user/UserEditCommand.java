package by.vsu.kovzov.controllers.commands.user;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.SpecializationService;
import by.vsu.kovzov.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserEditCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            UserService service = getServiceFactory().getUserService();
            User user = service.findById(id);
            req.setAttribute("user_edit", user);
        }
        return null;
    }
}
