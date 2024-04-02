package ru.kpfu.itis.paramonov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.exceptions.RegistrationException;
import ru.kpfu.itis.paramonov.exceptions.UserNotFoundException;
import ru.kpfu.itis.paramonov.mappers.UserModelMapper;
import ru.kpfu.itis.paramonov.model.User;
import ru.kpfu.itis.paramonov.repository.UserRepository;
import ru.kpfu.itis.paramonov.security.PasswordValidator;
import ru.kpfu.itis.paramonov.service.UserService;
import ru.kpfu.itis.paramonov.utils.Resources;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private PasswordValidator passwordValidator;

    private UserModelMapper userModelMapper;

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto get(int id) {
        return null;
    }

    @Override
    public UserDto get(String login) {
        User user = userRepository.findByLogin(login);
        return userModelMapper.fromModel(user);
    }

    @Override
    public UserDto get(String login, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        User user = userRepository.findByLoginAndPassword(login, encryptedPassword);
        return userModelMapper.fromModel(user);
    }

    @Override
    public UserDto save(String login, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new RegistrationException(Resources.PASSWORD_NOT_MATCH);
        }
        PasswordValidator.Result result = passwordValidator.validate(password);
        if (result.getClass().equals(PasswordValidator.Result.Incorrect.class)) {
            throw new RegistrationException(
                    ((PasswordValidator.Result.Incorrect) result).getMessage());
        }
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return get(user.login);
    }
}
