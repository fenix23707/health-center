package by.vsu.kovzov.controllers.commands;

import by.vsu.kovzov.controllers.commands.auth.LoginCommand;
import by.vsu.kovzov.controllers.commands.auth.LogoutCommand;
import by.vsu.kovzov.controllers.commands.doctor.DoctorEditCommand;
import by.vsu.kovzov.controllers.commands.doctor.DoctorListCommand;
import by.vsu.kovzov.controllers.commands.doctor.DoctorSaveCommand;
import by.vsu.kovzov.controllers.commands.specialization.SpecializationEditCommand;
import by.vsu.kovzov.controllers.commands.specialization.SpecializationListCommand;
import by.vsu.kovzov.controllers.commands.specialization.SpecializationSaveCommand;
import by.vsu.kovzov.controllers.commands.user.UserListCommand;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private static final Map<String, Class<? extends Command>> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("/", MainCommand.class);
        COMMANDS.put("/index", MainCommand.class);
        COMMANDS.put("/login", LoginCommand.class);
        COMMANDS.put("/logout", LogoutCommand.class);

        COMMANDS.put("/user/list", UserListCommand.class);

        COMMANDS.put("/specialization/list", SpecializationListCommand.class);
        COMMANDS.put("/specialization/edit", SpecializationEditCommand.class);
        COMMANDS.put("/specialization/save", SpecializationSaveCommand.class);

        COMMANDS.put("/doctor/list", DoctorListCommand.class);
        COMMANDS.put("/doctor/edit", DoctorEditCommand.class);
        COMMANDS.put("/doctor/save", DoctorSaveCommand.class);
    }

    @SneakyThrows
    public static Optional<Command> getCommand(String url) {
        Class<? extends Command> action = COMMANDS.get(url);
        if (action != null) {
            return Optional.of(action.getConstructor().newInstance());
        } else {
            return Optional.empty();
        }
    }
}
