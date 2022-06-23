package by.vsu.kovzov.controllers.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Data
@AllArgsConstructor
public class CommandResult {


    public CommandResult(String url, Type type, Map<String, String> params) {
        this.url = url + getParams(params);
        this.type = type;
    }

    String url;
    Type type;

    public enum Type {
        FORWARD, REDIRECT
    }

    @SneakyThrows
    private String getParam(String name, String value) {
        return format("%s=%s", name, URLEncoder.encode(value, "UTF-8"));
    }

    private String getParams(Map<String, String> params) {
        return "?" + params.entrySet().stream()
                .map(e -> format("%s=%s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("&"));
    }
}
