package by.vsu.kovzov.services.exceptions;

import lombok.Value;

@Value
public class ServiceException extends RuntimeException {

    int httpCode;

    public ServiceException(int httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }
}
