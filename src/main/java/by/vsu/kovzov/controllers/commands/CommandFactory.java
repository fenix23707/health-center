package by.vsu.kovzov.controllers.commands;

import jakarta.servlet.ServletException;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private static final Map<String, Class<? extends Command>> COMMANDS = new HashMap<>();

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
