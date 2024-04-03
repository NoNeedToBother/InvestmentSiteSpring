package ru.kpfu.itis.paramonov.service;

import ru.kpfu.itis.paramonov.dto.CommentDto;

public interface CommentService {
    CommentDto save(long postId, long commenterId, String content);
}
