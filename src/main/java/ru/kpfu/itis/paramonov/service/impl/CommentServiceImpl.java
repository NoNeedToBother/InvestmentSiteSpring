package ru.kpfu.itis.paramonov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.paramonov.dto.CommentDto;
import ru.kpfu.itis.paramonov.mappers.CommentModelMapper;
import ru.kpfu.itis.paramonov.model.Comment;
import ru.kpfu.itis.paramonov.model.Post;
import ru.kpfu.itis.paramonov.model.User;
import ru.kpfu.itis.paramonov.repository.CommentRepository;
import ru.kpfu.itis.paramonov.repository.PostRepository;
import ru.kpfu.itis.paramonov.repository.UserRepository;
import ru.kpfu.itis.paramonov.service.CommentService;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private UserRepository userRepository;

    private CommentModelMapper commentModelMapper;

    @Override
    public CommentDto save(long postId, long commenterId, String content) {
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        User author = userRepository.findById(commenterId).orElseThrow(RuntimeException::new);
        Comment comment = Comment.builder()
                .author(author)
                .post(post)
                .content(content)
                .build();
        Comment res = commentRepository.saveAndFlush(comment);
        return commentModelMapper.fromModel(res);
    }
}
