package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Specialization;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

public class SpecializationSaveCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        getServiceFactory().getSpecializationService().save(getSpecialization(req));
        return new CommandResult("/specialization/list.html", CommandResult.Type.FORWARD);
    }

    private Specialization getSpecialization(HttpServletRequest req) {
        Integer id = null;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (Exception e) {}
        return Specialization.builder()
                .id(id)
                .name(req.getParameter("name"))
                .narrow(req.getParameter("narrow") != null)
                .wageRate(new BigDecimal(req.getParameter("wageRate")) )
                .build();
    }

}
