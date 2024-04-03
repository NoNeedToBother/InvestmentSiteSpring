package ru.kpfu.itis.paramonov.service;

import ru.kpfu.itis.paramonov.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto get(long id);

    UserDto get(String login);

    UserDto get(String login, String password);

    UserDto save(String login, String email, String password, String confirmPassword);
}
