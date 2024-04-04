package ru.kpfu.itis.paramonov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.exceptions.RegistrationException;
import ru.kpfu.itis.paramonov.mappers.UserModelMapper;
import ru.kpfu.itis.paramonov.model.Role;
import ru.kpfu.itis.paramonov.model.User;
import ru.kpfu.itis.paramonov.repository.RoleRepository;
import ru.kpfu.itis.paramonov.repository.UserRepository;
import ru.kpfu.itis.paramonov.security.PasswordValidator;
import ru.kpfu.itis.paramonov.service.UserService;
import ru.kpfu.itis.paramonov.utils.Resources;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private PasswordValidator passwordValidator;

    private UserModelMapper userModelMapper;

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto get(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> userModelMapper.fromModel(value)).orElse(null);
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
            throw new RegistrationException(Resources.PASSWORD_NOT_MATCH_EXCEPTION);
        }
        else {
            PasswordValidator.Result result = passwordValidator.validate(password);
            if (result.getClass().equals(PasswordValidator.Result.Incorrect.class)) {
                throw new RegistrationException(
                        ((PasswordValidator.Result.Incorrect) result).getMessage());
            } else {
                User user = User.builder()
                        .login(login)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .likes(0)
                        .profilePicture(Resources.DEFAULT_PROFILE_PICTURE_URL)
                        .build();
                userRepository.save(user);
                Role role = roleRepository.findByName(Role.Value.USER.toString());
                user = userRepository.findByLogin(login);
                userRepository.addRole(role.getId(), user.getId());
                return get(login);
            }
        }
    }

    @Override
    public UserDto update(Map<String, String> params, Long id) {
        for (String key : params.keySet()) {
            String value = params.get(key);

            switch (key) {
                case "bio":
                    userRepository.updateBioById(value, id);
                    break;
                case "country":
                    userRepository.updateCountryById(value, id);
                    break;
                case "email":
                    userRepository.updateEmailById(value, id);
                    break;
                case "lastname":
                    userRepository.updateLastnameById(value, id);
                    break;
                case "name":
                    userRepository.updateNameById(value, id);
                    break;
                case "login":
                    userRepository.updateLoginById(value, id);
                    break;
            }
        }
        return get(id);
    }

    @Override
    public UserDto update(String profilePicture, Long id) {
        userRepository.updateProfilePictureById(profilePicture, id);
        return get(id);
    }

    @Override
    public void addRole(Role.Value roleValue, Long id) {
        Role role = roleRepository.findByName(roleValue.name());
        userRepository.addRole(role.getId(), id);
    }

    @Override
    @Transactional
    public UserDto updateLikes(Long receiverId, Long senderId) {
        userRepository.updateLikes(receiverId, senderId);
        userRepository.addLike(receiverId);
        return get(receiverId);
    }
}
