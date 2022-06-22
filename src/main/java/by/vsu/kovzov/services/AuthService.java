package by.vsu.kovzov.services;

import by.vsu.kovzov.models.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> signin(String login, String password);
}
