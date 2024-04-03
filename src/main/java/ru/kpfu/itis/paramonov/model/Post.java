package ru.kpfu.itis.paramonov.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private User author;

    @Column(name = "post_img_url")
    private String imageUrl;

    @Column(unique = true)
    private String title;

    private String content;

    @Column(name = "description")
    private String desc;

    @Column(name = "date_posted")
    @CreationTimestamp
    private Timestamp datePosted;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Comment> comments;
}
