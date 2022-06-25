package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.services.*;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService();

    AuthService getAuthService();

    SpecializationService getSpecializationService();

    DoctorService getDoctorService();

    SalaryService getSalaryService();

    EmployeeService getEmployeeService();

    HttpRequestService getHttpRequestService();
}
