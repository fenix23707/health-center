package by.vsu.kovzov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Doctor extends Person{
    Date employmentDate;
    BigDecimal salary;
    Specialization specialization;
    Integer branchNo;
}
