package ru.kpfu.itis.paramonov.security;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.paramonov.utils.Resources;

public class PasswordValidator {

    private final int MIN_PASSWORD_LENGTH = 8;

    public Result validate(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return new Result.Incorrect(Resources.SHORT_PASSWORD_EXCEPTION);
        }
        return new Result.Correct();
    }

    public static class Result {
        public static class Correct extends Result { }

        @AllArgsConstructor
        public static class Incorrect extends Result {
            private String message;

            public String getMessage() {
                return message;
            }
        }
    }
}
