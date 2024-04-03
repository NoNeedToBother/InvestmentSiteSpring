package ru.kpfu.itis.paramonov.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.paramonov.dto.RegistrationForm;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.exceptions.RegistrationException;
import ru.kpfu.itis.paramonov.service.UserService;
import ru.kpfu.itis.paramonov.utils.Params;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private UserService userService;

    @GetMapping
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping
    public String register(HttpSession httpSession, RegistrationForm form, Model model) {
        String login = form.getLogin();
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmpassword();
        UserDto user;
        try {
            user = userService.save(login, email, password, confirmPassword);
            httpSession.setAttribute(Params.SESSION_USER_KEY, user);
            return "redirect:/profile";
        } catch (RegistrationException ex) {
            return "redirect:/register";
        }
    }
}
