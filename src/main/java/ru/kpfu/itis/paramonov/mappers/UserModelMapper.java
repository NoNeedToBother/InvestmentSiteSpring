package ru.kpfu.itis.paramonov.mappers;

import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserModelMapper implements ModelMapper<User, UserDto> {
    @Override
    public UserDto fromModel(User model) {
        if (model == null) return null;
        int likes = (model.getLikes() == null) ? 0 : model.getLikes();
        return new UserDto(
                model.getId(),
                model.getName(),
                model.getLastname(),
                model.getLogin(),
                model.getProfilePicture(),
                model.getBio(),
                model.getEmail(),
                model.getDateRegistered(),
                model.getCountry(),
                likes,
                getLikeSenders(model.getLikeSenders())
        );
    }

    private List<Long> getLikeSenders(Set<User> likeSenders) {
        List<Long> res = new ArrayList<>();
        for (User likeSender: likeSenders) {
            res.add(likeSender.getId());
        }
        return res;
    }
}
