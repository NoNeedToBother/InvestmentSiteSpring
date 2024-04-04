package ru.kpfu.itis.paramonov.mappers;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.paramonov.dto.RoleDto;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.model.Role;
import ru.kpfu.itis.paramonov.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserModelMapper implements ModelMapper<User, UserDto> {

    private RoleModelMapper roleModelMapper;

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
                getLikeSenders(model.getLikeSenders()),
                getRoles(model.getRoles())
        );
    }

    private List<Long> getLikeSenders(Set<User> likeSenders) {
        return likeSenders.stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }

    private List<RoleDto> getRoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> roleModelMapper.fromModel(role))
                .collect(Collectors.toList());
    }
}
