package ru.kpfu.itis.paramonov.dto;

import lombok.Value;

@Value
public class RegistrationForm {
    private String login;

    private String email;

    private String password;

    private String confirmpassword;
}
