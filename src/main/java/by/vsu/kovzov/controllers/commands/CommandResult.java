package by.vsu.kovzov.controllers.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class CommandResult {


    public CommandResult(String url, String message, Type type) {
        this.url = url + getParam("message", message);
        this.type = type;
    }

    String url;
    Type type;

    public enum Type {
        FORWARD, REDIRECT
    }

    @SneakyThrows
    private String getParam(String name, String value) {
        return String.format("?%s=%s", name, URLEncoder.encode(value, "UTF-8"));
    }
}
