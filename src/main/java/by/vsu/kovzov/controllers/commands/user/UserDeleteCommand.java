package by.vsu.kovzov.controllers.commands.user;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.UserService;
import com.google.common.primitives.Longs;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

public class UserDeleteCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Longs.tryParse(firstNonNull(req.getParameter("id"), ""));
        String msg = "Пользователь удален";
        UserService userService = getServiceFactory().getUserService();
        if (id == null || !userService.delete(id)) {
            msg = "Не удалось удалить пользователя";
        }

        return new CommandResult("/user/list.html", CommandResult.Type.REDIRECT, Map.of("message", msg));
    }
}
