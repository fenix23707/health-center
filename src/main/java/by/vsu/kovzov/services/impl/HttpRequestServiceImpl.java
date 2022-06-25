package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.models.Config;
import by.vsu.kovzov.models.SortConfig;
import by.vsu.kovzov.services.HttpRequestService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.util.Optional;

public class HttpRequestServiceImpl implements HttpRequestService {
    @Override
    public Config getConfig(HttpServletRequest request) {
        Config config = new Config();

        Optional<SortConfig> sortConfig = getSortConfig(request);
        if (sortConfig.isPresent()) {
            config.setSortConfig(sortConfig.get());
        }

        return config;
    }

    private Optional<SortConfig> getSortConfig(HttpServletRequest request) {
        Optional<SortConfig> optional = Optional.empty();
        try {
            SortConfig.Order order = SortConfig.Order.valueOf(request.getParameter("order").toUpperCase(Locale.ROOT));
            String column = request.getParameter("column");
            if (column != null && !column.isBlank()) {
                optional = Optional.of(new SortConfig(column, order));
            }
        } catch (Exception e) {
        }
        return optional;
    }
}
