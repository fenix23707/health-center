package by.vsu.kovzov.models;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
@SuperBuilder
public class Employee extends Person {
    Date employmentDate;
    BigDecimal salary;
}
