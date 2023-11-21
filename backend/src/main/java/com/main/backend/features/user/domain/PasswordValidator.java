package com.main.backend.features.user.domain;

public class PasswordValidator {
    public static boolean isPasswordValid(String password) {
        return password.length() >= 8
                && containsDigit(password)
                && containsUppercase(password)
                && containsLowercase(password);
    }

    private static boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private static boolean containsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private static boolean containsLowercase(String password) {
        return password.matches(".*[a-z].*");
    }

}
