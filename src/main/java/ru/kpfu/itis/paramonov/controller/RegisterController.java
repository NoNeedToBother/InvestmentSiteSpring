package ru.kpfu.itis.paramonov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.paramonov.dto.RegistrationForm;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.exceptions.RegistrationException;
import ru.kpfu.itis.paramonov.repository.UserRepository;
import ru.kpfu.itis.paramonov.service.UserService;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String register(RegistrationForm form) {
        String login = form.getLogin();
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmpassword();
        UserDto user;
        try {
            user = userService.save(login, email, password, confirmPassword);
            log.info(user.getLogin());
            return "redirect:/profile";
        } catch (RegistrationException ex) {
            return "redirect:/register";
        }
    }
}
