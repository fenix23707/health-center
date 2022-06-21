package by.vsu.kovzov.controllers.commands;

import lombok.Data;

@Data
public class CommandResult {

    String url;
    Type type;

    public enum Type {
        FORWARD, REDIRECT
    }
}
