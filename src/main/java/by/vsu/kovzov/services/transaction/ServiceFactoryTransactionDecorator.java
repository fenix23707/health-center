package by.vsu.kovzov.services.transaction;

import by.vsu.kovzov.services.*;
import by.vsu.kovzov.utils.factories.ServiceFactory;

import java.lang.reflect.Proxy;

public class ServiceFactoryTransactionDecorator implements ServiceFactory {

    private ServiceFactory serviceFactory;

    public ServiceFactoryTransactionDecorator(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public UserService getUserService() {
        return createTransactionProxy(serviceFactory.getUserService(), UserService.class);
    }

    @Override
    public AuthService getAuthService() {
        return createTransactionProxy(serviceFactory.getAuthService(), AuthService.class);
    }

    @Override
    public SpecializationService getSpecializationService() {
        return createTransactionProxy(serviceFactory.getSpecializationService(), SpecializationService.class);
    }

    @Override
    public DoctorService getDoctorService() {
        return createTransactionProxy(serviceFactory.getDoctorService(), DoctorService.class);
    }

    @Override
    public SalaryService getSalaryService() {
        return createTransactionProxy(serviceFactory.getSalaryService(), SalaryService.class);
    }

    @Override
    public EmployeeService getEmployeeService() {
        return serviceFactory.getEmployeeService();
    }

    @Override
    public HttpRequestService getHttpRequestService() {
        return serviceFactory.getHttpRequestService();
    }

    @Override
    public ValidateService getValidateService() {
        return serviceFactory.getValidateService();
    }

    private <T> T createTransactionProxy(Object target, Class<T> aClass) {
        TransactionHandler handler = new TransactionHandler(target);
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{aClass}, handler);
    }
}
