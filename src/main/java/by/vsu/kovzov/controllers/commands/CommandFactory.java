package by.vsu.kovzov.controllers.commands;

import by.vsu.kovzov.controllers.commands.auth.LoginCommand;
import by.vsu.kovzov.controllers.commands.auth.LogoutCommand;
import by.vsu.kovzov.controllers.commands.doctor.DoctorDeleteCommand;
import by.vsu.kovzov.controllers.commands.doctor.DoctorEditCommand;
import by.vsu.kovzov.controllers.commands.doctor.DoctorListCommand;
import by.vsu.kovzov.controllers.commands.doctor.DoctorSaveCommand;
import by.vsu.kovzov.controllers.commands.specialization.SpecializationDeleteCommand;
import by.vsu.kovzov.controllers.commands.specialization.SpecializationEditCommand;
import by.vsu.kovzov.controllers.commands.specialization.SpecializationListCommand;
import by.vsu.kovzov.controllers.commands.specialization.SpecializationSaveCommand;
import by.vsu.kovzov.controllers.commands.user.UserDeleteCommand;
import by.vsu.kovzov.controllers.commands.user.UserEditCommand;
import by.vsu.kovzov.controllers.commands.user.UserListCommand;
import by.vsu.kovzov.controllers.commands.user.UserSaveCommand;
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
        COMMANDS.put("/user/edit", UserEditCommand.class);
        COMMANDS.put("/user/save", UserSaveCommand.class);
        COMMANDS.put("/user/delete", UserDeleteCommand.class);

        COMMANDS.put("/specialization/list", SpecializationListCommand.class);
        COMMANDS.put("/specialization/edit", SpecializationEditCommand.class);
        COMMANDS.put("/specialization/save", SpecializationSaveCommand.class);
        COMMANDS.put("/specialization/delete", SpecializationDeleteCommand.class);

        COMMANDS.put("/doctor/list", DoctorListCommand.class);
        COMMANDS.put("/doctor/edit", DoctorEditCommand.class);
        COMMANDS.put("/doctor/save", DoctorSaveCommand.class);
        COMMANDS.put("/doctor/delete", DoctorDeleteCommand.class);
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
