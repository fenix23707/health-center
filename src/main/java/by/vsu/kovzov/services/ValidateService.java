package by.vsu.kovzov.services;

import by.vsu.kovzov.validators.RequiredValidator;
import by.vsu.kovzov.services.exceptions.ValidationException;
import by.vsu.kovzov.validators.Validator;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.google.common.base.MoreObjects.firstNonNull;

public interface ValidateService {
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");

    default String getString(String field, HttpServletRequest req, Validator<String>... validators) {
        String value = req.getParameter(field);

        Arrays.stream(validators).forEach(validator -> {
            if (!validator.test(value)) {
                throw new ValidationException(field + " is not valid. " + validator.getMessage());
            }
        });
        return value;
    }

    default Integer getInt(String field, HttpServletRequest req, Validator<String>... validators) {
        String value = getString(field, req, validators);
        return Ints.tryParse(firstNonNull(value, ""));
    }

    default Long getLong(String field, HttpServletRequest req, Validator<String>... validators) {
        String value = getString(field, req, validators);
        return Longs.tryParse(firstNonNull(value, ""));
    }

    default BigDecimal getBigDecimal(String field, HttpServletRequest req, Validator<String>... validators) {
        String value = getString(field, req, validators);
        return new BigDecimal(value);
    }

    default Date getDate(String field, HttpServletRequest req, Validator<String>... validators) {
        String value = getString(field, req, validators);
        try {
            return DATE_FORMAT.parse(value);
        } catch (ParseException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
