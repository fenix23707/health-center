package by.vsu.kovzov.models;

import lombok.Data;

import java.util.Date;

@Data
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
