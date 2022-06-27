package by.vsu.kovzov.controllers.commands.user;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.ValidateService;
import by.vsu.kovzov.validators.Validator;
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
        ValidateService validate = getServiceFactory().getValidateService();
        User user = new User();
        user.setId(validate.getLong("id", req));
        user.setLogin(validate.getString("login", req, Validator.REQUIRED));
        user.setPassword(validate.getString("password", req));
        user.setRole(User.Role.valueOf(req.getParameter("role")));
        return user;
    }


}
