package ru.kpfu.itis.paramonov.mappers;

import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.model.User;

@Slf4j
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
                likes
        );
    }
}
