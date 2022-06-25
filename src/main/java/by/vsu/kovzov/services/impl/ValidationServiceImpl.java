package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.services.ValidateService;
import by.vsu.kovzov.validators.Validator;
import jakarta.servlet.http.HttpServletRequest;

public class ValidationServiceImpl implements ValidateService {
    @Override
    public String getString(String field, HttpServletRequest req, Validator<String>... validators) {
        return ValidateService.super.getString(field, req, validators);
    }

    @Override
    public Integer getInt(String field, HttpServletRequest req, Validator<String>... validators) {
        return ValidateService.super.getInt(field, req, validators);
    }

    @Override
    public Long getLong(String field, HttpServletRequest req, Validator<String>... validators) {
        return ValidateService.super.getLong(field, req, validators);
    }
}
