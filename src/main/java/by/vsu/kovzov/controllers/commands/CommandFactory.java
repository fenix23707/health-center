package by.vsu.kovzov.controllers.commands;

import by.vsu.kovzov.controllers.commands.auth.LoginCommand;
import by.vsu.kovzov.controllers.commands.auth.LogoutCommand;
import by.vsu.kovzov.controllers.commands.user.UserListCommand;
import jakarta.servlet.ServletException;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private static final Map<String, Class<? extends Command>> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("/login", LoginCommand.class);
        COMMANDS.put("/logout", LogoutCommand.class);

        COMMANDS.put("/user/list", UserListCommand.class);
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