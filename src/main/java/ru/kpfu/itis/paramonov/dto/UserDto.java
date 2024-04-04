package ru.kpfu.itis.paramonov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;
    private String lastname;

    private String login;

    private String profilePicture;

    private String bio;

    private String email;

    private Timestamp dateRegistered;

    private String country;

    private Integer likes;

    private List<Long> likeSenders;

    private List<RoleDto> roles;
}

