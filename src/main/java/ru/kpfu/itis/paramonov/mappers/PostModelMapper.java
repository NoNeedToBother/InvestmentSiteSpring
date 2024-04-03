package ru.kpfu.itis.paramonov.mappers;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.paramonov.dto.CommentDto;
import ru.kpfu.itis.paramonov.dto.PostDto;
import ru.kpfu.itis.paramonov.model.Comment;
import ru.kpfu.itis.paramonov.model.Post;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PostModelMapper implements ModelMapper<Post, PostDto>{

    private CommentModelMapper commentModelMapper;

    private UserModelMapper userModelMapper;

    @Override
    public PostDto fromModel(Post model) {
        if (model == null) return null;
        return new PostDto(
                model.getId(),
                userModelMapper.fromModel(model.getAuthor()),
                model.getImageUrl(),
                model.getTitle(),
                model.getContent(),
                model.getDesc(),
                model.getDatePosted(),
                sortCommentByDate(model.getComments())
        );
    }

    private List<CommentDto> sortCommentByDate(Set<Comment> comments) {
        return comments.stream()
                .sorted((c1, c2) -> {
                    Timestamp datePosted1 = c1.getDatePublished();
                    Timestamp datePosted2 = c2.getDatePublished();
                    return (-1) * datePosted1.compareTo(datePosted2);
                })
                .map(comment -> commentModelMapper.fromModel(comment))
                .collect(Collectors.toList());
    }
}
