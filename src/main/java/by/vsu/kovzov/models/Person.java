package by.vsu.kovzov.models;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
public class Person {
    Long id;
    String name;
    String surname;
    String patronymic;
    Sex sex;
    Date dob;

    public enum Sex{
        MAN, WOMAN
    }
}
