package ru.kpfu.itis.paramonov.mappers;

import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.model.User;

public class UserModelMapper implements ModelMapper<User, UserDto> {
    @Override
    public UserDto fromModel(User model) {
        if (model == null) return null;
        int likes = (model.likes == null) ? 0 : model.likes;
        return new UserDto(
                model.id,
                model.name,
                model.lastname,
                model.login,
                model.profilePicture,
                model.bio,
                model.email,
                model.dateRegistered,
                model.country,
                likes
        );
    }
}
