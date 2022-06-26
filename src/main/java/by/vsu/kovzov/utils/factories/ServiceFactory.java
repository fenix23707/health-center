package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.services.*;
import by.vsu.kovzov.services.ValidateService;

public interface ServiceFactory {
    UserService getUserService();

    AuthService getAuthService();

    SpecializationService getSpecializationService();

    DoctorService getDoctorService();

    SalaryService getSalaryService();

    EmployeeService getEmployeeService();

    HttpRequestService getHttpRequestService();

    ValidateService getValidateService();
}
