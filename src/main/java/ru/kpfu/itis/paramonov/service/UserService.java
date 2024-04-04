package ru.kpfu.itis.paramonov.service;

import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.model.Role;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserDto> getAll();

    UserDto get(long id);

    UserDto get(String login);

    UserDto get(String login, String password);

    UserDto save(String login, String email, String password, String confirmPassword);

    UserDto update(Map<String, String> params, Long id);

    UserDto update(String profilePicture, Long id);

    void addRole(Role.Value roleValue, Long id);
}
