package ru.kpfu.itis.paramonov.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.paramonov.dto.CommentDto;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.service.CommentService;
import ru.kpfu.itis.paramonov.service.PostService;
import ru.kpfu.itis.paramonov.service.UserService;
import ru.kpfu.itis.paramonov.utils.Params;
import ru.kpfu.itis.paramonov.utils.Result;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.util.Map;

@RequestMapping("/getutil")
@RestController
@AllArgsConstructor
public class UtilController {

    private PostService postService;

    private CommentService commentService;

    private UserService userService;

    private HttpSession httpSession;

    @GetMapping
    public String process(
            @RequestParam Map<String, String> params,
            @SessionAttribute(Params.SESSION_USER_KEY) UserDto user
            ) {
        String task = params.get(Params.REQUEST_TASK_PARAM);
        switch (task) {
            case Params.TASK_CHECK_TITLE:
                String title = params.get(Params.REQUEST_POST_TITLE_PARAM);
                if (postService.checkTitle(title)) return "ok";
                else return "not_ok";
            case Params.TASK_GET_USER_DATA:
                return toJson(user);
            case Params.TASK_UPDATE_COMMENTS:
                return updateComments(params);
            case Params.TASK_UPDATE_USER_DATA:
                Long userId = Long.parseLong(params.get(Params.REQUEST_ID_PARAM));
                return updateUserData(params, userId);
            default:
                throw new HTTPException(Params.BAD_REQUEST_HTTP_CODE);
        }
    }

    private String updateComments(Map<String, String> params) {
        int commenterId = Integer.parseInt(params.get(Params.REQUEST_COMMENTER_ID_PARAM));
        String content = params.get(Params.REQUEST_CONTENT_PARAM);
        int postId = Integer.parseInt(params.get(Params.REQUEST_POST_ID_PARAM));
        try {
            CommentDto comment = commentService.save(postId, commenterId, content);
            Result<CommentDto> result = new Result<>("ok", comment);
            return toJson(result);
        } catch (Exception e) {
            Result<CommentDto> result = new Result<>("not_ok", null);
            return toJson(result);
        }
    }

    private String updateUserData(Map<String, String> params, Long id) {
        try {
            UserDto user = userService.update(params, id);
            httpSession.setAttribute(Params.SESSION_USER_KEY, user);
            return "ok";
        } catch (Exception e) {
            return "not_ok";
        }
    }

    private String toJson(Object entity) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(entity);
    }
}
