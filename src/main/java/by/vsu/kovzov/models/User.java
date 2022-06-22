package by.vsu.kovzov.models;

import lombok.Data;
import lombok.Getter;

@Data
public class User {

    Long id;
    String login;
    String password;
    Role role;

    @Getter
    public enum Role {
        ADMIN("Администратор"), REGISTRATOR("регистратор");

        private String name;

        Role(String name) {
            this.name = name;
        }
    }

}
