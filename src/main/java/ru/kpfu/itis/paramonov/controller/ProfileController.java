package ru.kpfu.itis.paramonov.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.service.UserService;
import ru.kpfu.itis.paramonov.utils.Params;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private UserService userService;

    @GetMapping
    public String getProfilePage(@RequestParam(name = "id", required = false) Integer id,
                                 @SessionAttribute(value = Params.SESSION_USER_KEY, required = false) UserDto user, Model model) {
        if (id != null) {
            UserDto profileUser = userService.get(id);
            if (profileUser != null) {
                boolean isSameUser;
                if (user != null) isSameUser = profileUser.getId().equals(user.getId());
                else isSameUser = false;

                saveToModel(model, profileUser, isSameUser);
                return "profile";
            } else return "redirect:/error";
        } else if (user == null) {
            return "redirect:/error";
        }
        else {
            saveToModel(model, user, true);
            return "profile";
        }
    }

    private void saveToModel(Model model, UserDto profileUser, boolean isSame) {
        model.addAttribute(Params.MODEL_PROFILE_USER_KEY, profileUser);
        model.addAttribute(Params.MODEL_SAME_USER_KEY, isSame);
    }

}
