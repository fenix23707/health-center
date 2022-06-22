package by.vsu.kovzov.controllers.commands.auth;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.net.URLEncoder;
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
                return new CommandResult("/index.html", CommandResult.Type.FORWARD);
            } else {
                String message = "incorrect login or password";
                String url = "/login-form.html?message="
                        + URLEncoder.encode(message, "UTF-8");
                return new CommandResult(url, CommandResult.Type.FORWARD);
            }
        } else {
            return null;
        }
    }
}
