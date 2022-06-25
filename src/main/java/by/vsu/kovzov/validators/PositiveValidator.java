package by.vsu.kovzov.validators;

import java.math.BigDecimal;

public class PositiveValidator implements Validator<String> {

    @Override
    public String getMessage() {
        return "Поле должно быть больше 0";
    }

    @Override
    public boolean test(String s) {
        return new BigDecimal(s).compareTo(BigDecimal.ZERO) > 0;
    }
}
