package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.Constants;
import by.vsu.kovzov.models.Person;
import by.vsu.kovzov.services.EmployeeService;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class EmployeeServiceImpl extends AbstractService implements EmployeeService {
    public boolean isPensioner(Person person) {
        int age = calculateYears(person.getDob());
        return person.getSex() == Person.Sex.MAN && age >= Constants.MAN_RETIREMENT_AGE ||
                (person.getSex() == Person.Sex.WOMAN && age >= Constants.WOMAN_RETIREMENT_AGE);
    }
    
    @Override
    public int calculateYears(Date start) {
        LocalDate s = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return calculateYears(s);
    }

    @Override
    public int calculateYears(LocalDate start) {
        return calculateYears(start, LocalDate.now());
    }

    @Override
    public int calculateYears(LocalDate start, LocalDate end) {
        return Period.between(start, end).getYears();
    }
}
