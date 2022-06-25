package by.vsu.kovzov.validators;

public interface Validator<T> {
    String getMessage();

    boolean test(T s);

    Validator REQUIRED = new RequiredValidator();
    Validator POSITIVE = new PositiveValidator();
}
