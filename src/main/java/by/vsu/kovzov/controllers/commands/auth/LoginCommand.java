package by.vsu.kovzov.controllers.commands.auth;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.Optional;

public class LoginCommand extends Command {

    @Override
    @SneakyThrows
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            Optional<User> user = getServiceFactory().getAuthService().signin(login, password);
            if (user.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user.get());
                return new CommandResult("/index.html", CommandResult.Type.REDIRECT);
            } else {
                return new CommandResult("/login.html", CommandResult.Type.REDIRECT,
                        Map.of("message", "неверный логин или пароль"));
            }
        } else {
            return null;
        }
    }
}
