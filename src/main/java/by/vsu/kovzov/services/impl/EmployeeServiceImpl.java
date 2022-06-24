package by.vsu.kovzov.services.impl;

import by.vsu.kovzov.Constants;
import by.vsu.kovzov.models.Employee;
import by.vsu.kovzov.models.Person;
import by.vsu.kovzov.services.EmployeeService;
import by.vsu.kovzov.services.exceptions.ServiceException;
import org.apache.http.HttpStatus;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class EmployeeServiceImpl extends AbstractService implements EmployeeService {

    @Override
    public void checkEmploymentAge(Employee employee) {
        int age = calculateYears(employee.getDob(), employee.getEmploymentDate());
        if (age < 20) {
            throw new ServiceException(HttpStatus.SC_NOT_ACCEPTABLE, "age = " + age + " is not enough");
        }
    }

    public boolean isPensioner(Person person) {
        int age = calculateYears(person.getDob());
        return person.getSex() == Person.Sex.MAN && age >= Constants.MAN_RETIREMENT_AGE ||
                (person.getSex() == Person.Sex.WOMAN && age >= Constants.WOMAN_RETIREMENT_AGE);
    }

    @Override
    public int calculateYears(Date start) {
        return calculateYears(toLocalDate(start));
    }


    @Override
    public int calculateYears(Date start, Date end) {
        return calculateYears(toLocalDate(start), toLocalDate(end));
    }

    @Override
    public int calculateYears(LocalDate start) {
        return calculateYears(start, LocalDate.now());
    }

    @Override
    public int calculateYears(LocalDate start, LocalDate end) {
        return Period.between(start, end).getYears();
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
