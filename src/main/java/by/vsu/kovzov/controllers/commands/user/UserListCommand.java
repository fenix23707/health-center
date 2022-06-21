package by.vsu.kovzov.controllers.commands.user;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UserListCommand extends Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = getServiceFactory().getUserService();
        List<User> users = userService.getAll();
        req.setAttribute("users", users);
        return null;
    }
}
