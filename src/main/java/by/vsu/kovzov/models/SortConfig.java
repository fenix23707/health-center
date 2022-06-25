package by.vsu.kovzov.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortConfig {
    String column;
    Order order;

    public enum Order {
        ASC, DESC;
    }
}
