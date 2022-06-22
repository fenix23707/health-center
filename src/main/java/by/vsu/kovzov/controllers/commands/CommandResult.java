package by.vsu.kovzov.controllers.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandResult {

    String url;
    Type type;

    public enum Type {
        FORWARD, REDIRECT
    }
}
