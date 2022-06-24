package by.vsu.kovzov.controllers.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;
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

    private String getParams(Map<String, String> params) {
        return "?" + params.entrySet().stream()
                .map(e -> {
                    try {
                        return format("%s=%s", e.getKey(), URLEncoder.encode(e.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(Collectors.joining("&"));
    }
}
