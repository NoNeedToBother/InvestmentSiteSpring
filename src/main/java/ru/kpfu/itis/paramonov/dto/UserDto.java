package ru.kpfu.itis.paramonov.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
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

    public UserDto(Long id, String name, String lastname, String login, String profilePicture, String bio,
                   String email, Timestamp dateRegistered, String country, Integer likes) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.email = email;
        this.dateRegistered = dateRegistered;
        this.country = country;
        this.likes = likes;
    }
}

