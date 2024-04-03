package ru.kpfu.itis.paramonov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Long id;

    private UserDto commenter;

    private Timestamp datePublished;

    private String content;

}
