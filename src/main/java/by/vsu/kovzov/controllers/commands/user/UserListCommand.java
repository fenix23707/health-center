package by.vsu.kovzov.controllers.commands.user;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.HttpRequestService;
import by.vsu.kovzov.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UserListCommand extends Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = getServiceFactory().getUserService();
        HttpRequestService requestService = getServiceFactory().getHttpRequestService();
        List<User> users = userService.getAll(requestService.getConfig(req));
        req.setAttribute("users", users);
        req.setAttribute("order", req.getAttribute("order"));
        return null;
    }
}
