package by.vsu.kovzov.controllers.commands.auth;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        return new CommandResult("/index.html", CommandResult.Type.FORWARD);
    }
}
