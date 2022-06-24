package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.services.SpecializationService;
import com.google.common.primitives.Ints;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

public class SpecializationDeleteCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Ints.tryParse(firstNonNull(req.getParameter("specializationId"), ""));
        String msg = "Специальность удалена";
        SpecializationService specializationService = getServiceFactory().getSpecializationService();
        if (id == null || !specializationService.delete(id)) {
            msg = "Не удалось удалить специальность";
        }

        return new CommandResult("/specialization/list.html", CommandResult.Type.REDIRECT, Map.of("message", msg));
    }
}
