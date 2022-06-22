package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.models.User;
import by.vsu.kovzov.services.SpecializationService;
import by.vsu.kovzov.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class SpecializationListCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        SpecializationService specializationService = getServiceFactory().getSpecializationService();
        List<Specialization> specializations = specializationService.getAll();
        req.setAttribute("specializations", specializations);
        return null;
    }
}
