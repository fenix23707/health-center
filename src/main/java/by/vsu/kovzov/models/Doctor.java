package by.vsu.kovzov.models;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@SuperBuilder
public class Doctor extends Employee {

    Specialization specialization;
    Integer branchNo;
}
