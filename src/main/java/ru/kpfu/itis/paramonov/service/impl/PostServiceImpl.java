package ru.kpfu.itis.paramonov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.paramonov.dto.PostDto;
import ru.kpfu.itis.paramonov.mappers.PostModelMapper;
import ru.kpfu.itis.paramonov.model.Post;
import ru.kpfu.itis.paramonov.model.User;
import ru.kpfu.itis.paramonov.repository.PostRepository;
import ru.kpfu.itis.paramonov.repository.UserRepository;
import ru.kpfu.itis.paramonov.service.PostService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private UserRepository userRepository;

    private PostModelMapper postModelMapper;

    @Override
    public List<PostDto> getAll() {
        return postRepository
                .findAll()
                .stream().map( post ->
                        postModelMapper.fromModel(post)
                )
                .collect(Collectors.toList());
    }

    @Override
    public PostDto get(long id) {
        Post post = postRepository.findById(id).orElse(null);
        return postModelMapper.fromModel(post);
    }

    @Override
    public PostDto get(String title) {
        Post post = postRepository.findByTitle(title);
        return postModelMapper.fromModel(post);
    }

    @Override
    public PostDto save(long posterId, String imageUrl, String content, String description, String title) {
        User poster = userRepository.findById(posterId).orElseThrow(RuntimeException::new);
        Post post = Post.builder()
                .author(poster)
                .imageUrl(imageUrl)
                .content(content)
                .desc(description)
                .title(title)
                .build();
        postRepository.save(post);
        return get(title);
    }

    @Override
    public boolean checkTitle(String title) {
        return !postRepository.existsByTitle(title);
    }
}
