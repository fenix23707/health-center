package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Specialization;
import com.google.common.primitives.Ints;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import static com.google.common.base.MoreObjects.firstNonNull;

public class SpecializationSaveCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        getServiceFactory().getSpecializationService().save(getSpecialization(req));
        return new CommandResult("/specialization/list.html", CommandResult.Type.REDIRECT);
    }

    private Specialization getSpecialization(HttpServletRequest req) {
        Integer id = Ints.tryParse(firstNonNull(req.getParameter("id"), ""));
        return Specialization.builder()
                .id(id)
                .name(req.getParameter("name"))
                .narrow(req.getParameter("narrow") != null)
                .wageRate(new BigDecimal(req.getParameter("wageRate")))
                .build();
    }

}
