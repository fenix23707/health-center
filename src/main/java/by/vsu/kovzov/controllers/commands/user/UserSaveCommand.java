package by.vsu.kovzov.controllers.commands.user;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.primitives.Longs.tryParse;

public class UserSaveCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        getServiceFactory().getUserService().save(getUser(req));
        return new CommandResult("/user/list.html", CommandResult.Type.REDIRECT);
    }

    private User getUser(HttpServletRequest req) {
        User user = new User();
        user.setId(tryParse(firstNonNull(req.getParameter("id"), "")));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setRole(User.Role.valueOf(req.getParameter("role")));
        return user;
    }


}
