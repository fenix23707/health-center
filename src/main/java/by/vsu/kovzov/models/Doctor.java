package by.vsu.kovzov.models;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
public class Doctor extends Person{
    Date employmentDate;
    BigDecimal salary;
    Specialization specialization;
    Integer branchNo;
}
