package by.vsu.kovzov.utils.factories;

import by.vsu.kovzov.dao.DoctorDao;
import by.vsu.kovzov.dao.SpecializationDao;
import by.vsu.kovzov.dao.UserDao;
import by.vsu.kovzov.dao.postgres.DoctorDaoImpl;
import by.vsu.kovzov.dao.postgres.SpecializationDaoImpl;
import by.vsu.kovzov.dao.postgres.SpecializationIdentityMap;
import by.vsu.kovzov.dao.postgres.UserDaoImpl;
import by.vsu.kovzov.services.*;
import by.vsu.kovzov.services.impl.*;
import by.vsu.kovzov.utils.pool.ConnectionPool;
import by.vsu.kovzov.services.ValidateService;
import by.vsu.kovzov.services.impl.ValidationServiceImpl;
import lombok.SneakyThrows;

import java.sql.Connection;

public class ServiceFactoryImpl implements ServiceFactory{

    private Connection connection = null;
    private ComparatorFactory comparatorFactory = null;

    private UserDao userDao = null;
    private DoctorDao doctorDao = null;
    private SpecializationDao specializationDao = null;

    private UserService userService = null;
    private AuthService authService = null;
    private DoctorService doctorService = null;
    private SpecializationService specializationService = null;
    private SalaryService salaryService = null;
    private EmployeeService employeeService = null;
    private HttpRequestService requestService = null;
    private ValidateService validateService = null;

    @Override
    public UserService getUserService() {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl(getUserDao());
            userService = userServiceImpl;
        }

        return userService;
    }

    @Override
    public AuthService getAuthService() {
        if (authService == null) {
            AuthServiceImpl authServiceImpl = new AuthServiceImpl(getUserDao());
            authService = authServiceImpl;
        }

        return authService;
    }

    @Override
    public DoctorService getDoctorService() {
        if (doctorService == null) {
            DoctorServiceImpl doctorServiceImpl = new DoctorServiceImpl();
            doctorService = doctorServiceImpl;
            doctorServiceImpl.setDoctorDao(getDoctorDao());
            doctorServiceImpl.setSalaryService(getSalaryService());
            doctorServiceImpl.setEmployeeService(getEmployeeService());
            doctorServiceImpl.setSpecializationService(getSpecializationService());
        }
        return doctorService;
    }

    @Override
    public SpecializationService getSpecializationService() {
        if (specializationService == null) {
            SpecializationServiceImpl specializationServiceImpl = new SpecializationServiceImpl();
            specializationService = specializationServiceImpl;
            specializationServiceImpl.setComparatorFactory(getComparatorFactory());
            specializationServiceImpl.setSpecializationDao(getSpecializationDao());
            specializationServiceImpl.setDoctorService(getDoctorService());
        }

        return specializationService;
    }

    @Override
    public SalaryService getSalaryService() {
        if (salaryService == null) {
            SalaryServiceImpl salaryServiceImpl = new SalaryServiceImpl();
            salaryService = salaryServiceImpl;
            salaryServiceImpl.setSpecializationService(getSpecializationService());
            salaryServiceImpl.setEmployeeService(getEmployeeService());
        }

        return salaryService;
    }

    @Override
    public EmployeeService getEmployeeService() {
        if (employeeService == null) {
            EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
            employeeService = employeeServiceImpl;
        }
        return employeeService;
    }

    @Override
    public HttpRequestService getHttpRequestService() {
        if (requestService == null) {
            HttpRequestServiceImpl httpRequestServiceImpl = new HttpRequestServiceImpl();
            requestService = httpRequestServiceImpl;
        }
        return requestService;
    }

    @Override
    public ValidateService getValidateService() {
        if (validateService == null) {
            validateService = new ValidationServiceImpl();
        }
        return validateService;
    }

    protected UserDao getUserDao() {
        if (userDao == null) {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            userDao = userDaoImpl;
        }

        return userDao;
    }

    protected DoctorDao getDoctorDao() {
        if (doctorDao == null) {
            DoctorDaoImpl doctorDaoImpl = new DoctorDaoImpl();
            doctorDao = doctorDaoImpl;
        }
        return doctorDao;
    }

    protected SpecializationDao getSpecializationDao() {
        if (specializationDao == null) {
            SpecializationDaoImpl specializationDaoImpl = new SpecializationDaoImpl();
            specializationDao = new SpecializationIdentityMap(specializationDaoImpl);
        }

        return specializationDao;
    }

    protected ComparatorFactory getComparatorFactory() {
        if (comparatorFactory == null) {
            ComparatorFactoryImpl comparatorFactoryImpl = new ComparatorFactoryImpl();
            comparatorFactory = comparatorFactoryImpl;
        }
        return comparatorFactory;
    }
}
