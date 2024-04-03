package ru.kpfu.itis.paramonov.mappers;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.paramonov.dto.CommentDto;
import ru.kpfu.itis.paramonov.model.Comment;

@AllArgsConstructor
public class CommentModelMapper implements ModelMapper<Comment, CommentDto>{

    private UserModelMapper userModelMapper;

    @Override
    public CommentDto fromModel(Comment model) {
        return new CommentDto(
                model.getId(),
                userModelMapper.fromModel(model.getAuthor()),
                model.getDatePublished(),
                model.getContent()
        );
    }

}
