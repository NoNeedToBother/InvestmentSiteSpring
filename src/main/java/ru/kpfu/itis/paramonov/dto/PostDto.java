package ru.kpfu.itis.paramonov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;

    private UserDto poster;

    private String imageUrl;

    private String title;

    private String content;

    private String description;

    private Timestamp datePosted;

    private List<CommentDto> comments;
}
