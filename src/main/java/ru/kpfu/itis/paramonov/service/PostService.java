package ru.kpfu.itis.paramonov.service;

import ru.kpfu.itis.paramonov.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAll();

    PostDto get(long id);

    PostDto get(String title);

    PostDto save(long posterId, String imageUrl, String content, String description, String title);

}
