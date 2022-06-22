package by.vsu.kovzov.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SpecializationDto {
    Integer id;
    String name;
    boolean narrow;
    int doctorsNumber;
    BigDecimal wageRate;
    BigDecimal totalCost;
}
