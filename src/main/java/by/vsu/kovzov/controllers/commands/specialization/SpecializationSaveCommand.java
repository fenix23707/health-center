package by.vsu.kovzov.controllers.commands.specialization;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.models.Specialization;
import by.vsu.kovzov.services.ValidateService;
import by.vsu.kovzov.validators.Validator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import static by.vsu.kovzov.validators.Validator.POSITIVE;
import static by.vsu.kovzov.validators.Validator.REQUIRED;

public class SpecializationSaveCommand extends Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        getServiceFactory().getSpecializationService().save(getSpecialization(req));
        return new CommandResult("/specialization/list.html", CommandResult.Type.REDIRECT);
    }

    private Specialization getSpecialization(HttpServletRequest req) {
        ValidateService validate = getServiceFactory().getValidateService();
        return Specialization.builder()
                .id(validate.getInt("id", req))
                .name(validate.getString("name", req, REQUIRED))
                .narrow(req.getParameter("narrow") != null)
                .wageRate(validate.getBigDecimal("wageRate", req, REQUIRED, POSITIVE))
                .build();
    }

}
