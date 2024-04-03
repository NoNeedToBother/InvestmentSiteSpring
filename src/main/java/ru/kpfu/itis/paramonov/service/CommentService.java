package ru.kpfu.itis.paramonov.service;

public interface CommentService {
    void save(long postId, long commenterId, String content);
}
