package by.vsu.kovzov.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Specialization {
    String name;
    BigDecimal wageRate;
    boolean narrow;
}
