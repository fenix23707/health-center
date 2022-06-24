package by.vsu.kovzov.services;

import by.vsu.kovzov.models.Person;

import java.time.LocalDate;
import java.util.Date;

public interface EmployeeService {

    boolean isPensioner(Person person);

    int calculateYears(Date start);

    int calculateYears(LocalDate start);

    int calculateYears(LocalDate start, LocalDate end);
}
