package ru.kpfu.itis.paramonov.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.paramonov.dto.RegistrationForm;
import ru.kpfu.itis.paramonov.dto.RoleDto;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.exceptions.RegistrationException;
import ru.kpfu.itis.paramonov.service.UserService;
import ru.kpfu.itis.paramonov.utils.Params;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private UserService userService;

    private UserDetailsService userDetailsService;

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
            grantAuthorities(user);
            return "redirect:/profile";
        } catch (RegistrationException ex) {
            return "redirect:/register";
        }
    }

    private void grantAuthorities(UserDto userDto) {
        List<RoleDto> roles = userDto.getRoles();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authorities);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
