package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Config;
import jakarta.servlet.http.HttpServletRequest;

public interface HttpRequestService {

    Config getConfig(HttpServletRequest request);
}
