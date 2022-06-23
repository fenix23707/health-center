package by.vsu.kovzov.controllers.commands;

import by.vsu.kovzov.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MainCommand extends Command{

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                switch (user.getRole()) {
                    case ADMIN:
                        return new CommandResult("/user/list.html", CommandResult.Type.FORWARD);
                    case REGISTRATOR:
                        return new CommandResult("/specialization/list.html", CommandResult.Type.FORWARD);

                }
            }
        }
        return new CommandResult("/specialization/list.html", CommandResult.Type.FORWARD);
    }
}
