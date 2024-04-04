package ru.kpfu.itis.paramonov.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @Column(unique = true)
    private String login;

    private String password;

    @Column(name = "profile_picture")
    private String profilePicture;

    private String bio;

    private String email;

    @Column(name = "date_registered")
    @CreationTimestamp
    private Timestamp dateRegistered;

    private String country;

    private Integer likes;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Post> posts;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "receiver_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id")
    )
    private Set<User> likeSenders;
}