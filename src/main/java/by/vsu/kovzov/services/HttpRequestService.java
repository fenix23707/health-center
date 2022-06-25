package by.vsu.kovzov.services;

import by.vsu.kovzov.models.ListConfig;
import jakarta.servlet.http.HttpServletRequest;

public interface HttpRequestService {

    ListConfig getConfig(HttpServletRequest request);
}
