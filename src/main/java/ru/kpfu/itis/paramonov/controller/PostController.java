package ru.kpfu.itis.paramonov.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.paramonov.dto.PostDto;
import ru.kpfu.itis.paramonov.service.PostService;
import ru.kpfu.itis.paramonov.utils.Params;

import java.lang.reflect.Parameter;
import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @GetMapping("/post")
    public String getPostPage(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            return "error";
        }
        PostDto post = postService.get(id);
        model.addAttribute(Params.MODEL_POST_KEY, post);
        model.addAttribute(Params.MODEL_COMMENTS_KEY, post.getComments());
        model.addAttribute(Params.MODEL_AUTHOR_KEY, post.getPoster());
        return "post";
    }

    @GetMapping("/posts")
    public String getAllPostsPage(Model model) {
        List<PostDto> posts = postService.getAll();
        model.addAttribute(Params.MODEL_POSTS_KEY, posts);
        return "posts";
    }

    @GetMapping("/posts_submit")
    public String getPostSubmitPage() {
        return "post_submit";
    }
}
