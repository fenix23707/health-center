package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.services.AuthService;
import by.vsu.kovzov.services.DoctorService;
import by.vsu.kovzov.services.SpecializationService;
import by.vsu.kovzov.services.UserService;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService();

    AuthService getAuthService();

    SpecializationService getSpecializationService();

    DoctorService getDoctorService();
}
