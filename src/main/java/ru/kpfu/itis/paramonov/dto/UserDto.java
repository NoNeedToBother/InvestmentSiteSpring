package ru.kpfu.itis.paramonov.dto;

import java.sql.Timestamp;

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

    private int likes;

    public UserDto(Long id, String name, String lastname, String login, String profilePicture, String bio,
                   String email, Timestamp dateRegistered, String country, int likes) {
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

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getDateRegistered() {
        return dateRegistered;
    }

    public String getCountry() {
        return country;
    }

    public int getLikes() {
        return likes;
    }
}

