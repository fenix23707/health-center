package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.dto.SpecializationDto;
import by.vsu.kovzov.services.SpecializationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class SpecializationListCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        SpecializationService specializationService = getServiceFactory().getSpecializationService();
        List<SpecializationDto> specializations = specializationService.getAll();
        req.setAttribute("specializations", specializations);
        return null;
    }
}
