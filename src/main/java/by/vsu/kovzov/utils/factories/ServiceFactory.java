package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.services.UserService;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService();
}
