package by.vsu.kovzov.validators;

public class RequiredValidator implements Validator<String> {

    @Override
    public String getMessage() {
        return "this field is required";
    }

    @Override
    public boolean test(String s) {
        return s != null && !s.isBlank();
    }
}
