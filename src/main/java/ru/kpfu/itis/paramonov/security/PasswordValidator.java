package ru.kpfu.itis.paramonov.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.kpfu.itis.paramonov.utils.Resources;

public class PasswordValidator {

    private final int MIN_PASSWORD_LENGTH = 8;

    public Result validate(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return new Result.Incorrect(Resources.SHORT_PASSWORD_EXCEPTION);
        }
        boolean hasDigit = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (Character.isUpperCase(c)) hasUpperCase = true;
            if (Character.isLowerCase(c)) hasLowerCase = true;
        }
        if (!hasDigit) return new Result.Incorrect(Resources.NO_DIGIT_PASSWORD_EXCEPTION);
        if (!hasUpperCase) return new Result.Incorrect(Resources.NO_UPPERCASE_PASSWORD_EXCEPTION);
        if (!hasLowerCase) return new Result.Incorrect(Resources.NO_LOWERCASE_PASSWORD_EXCEPTION);
        return new Result.Correct();
    }

    public static class Result {
        public static class Correct extends Result { }

        @AllArgsConstructor
        @Getter
        public static class Incorrect extends Result {
            private String message;
        }
    }
}
