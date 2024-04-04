package ru.kpfu.itis.paramonov.controller;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.paramonov.dto.PostDto;
import ru.kpfu.itis.paramonov.dto.UserDto;
import ru.kpfu.itis.paramonov.service.PostService;
import ru.kpfu.itis.paramonov.service.UserService;
import ru.kpfu.itis.paramonov.utils.Params;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private Cloudinary cloudinary;

    private HttpSession httpSession;

    private UserService userService;

    private PostService postService;

    public static final String FILE_PATH_PREFIX = "/tmp";
    public static final int DIRECTORIES_COUNT = 100;

    @PostMapping
    public String upload(@RequestParam(name = "img", required = false) MultipartFile image, @RequestParam Map<String, Object> params) {
        String entity = (String) params.get(Params.REQUEST_ENTITY_PARAM);
        Long id = Long.parseLong((String) params.get(Params.REQUEST_ID_PARAM));
        if (entity != null) {
            switch (entity) {
                case Params.REQUEST_ENTITY_POST_PARAM:
                    try {
                        PostDto post = uploadPost(params, image, id);
                        return "redirect:/post?id=" + post.getId();
                    } catch (IOException e) {
                        return "redirect:/error";
                    }
                case Params.REQUEST_ENTITY_PROFILE_PICTURE_PARAM:
                    try {
                        String url = uploadImage(image);
                        UserDto userDto = userService.update(url, id);
                        httpSession.setAttribute(Params.SESSION_USER_KEY, userDto);
                        return "redirect:/profile";
                    } catch (IOException e) {
                        return "redirect:/error";
                    }
                default:
                    throw new HTTPException(Params.BAD_REQUEST_HTTP_CODE);
            }
        } else throw new HTTPException(Params.BAD_REQUEST_HTTP_CODE);
    }

    private PostDto uploadPost(Map<String, Object> params, MultipartFile image, Long id) throws IOException{
        String title = (String) params.get(Params.POST_REQUEST_POST_TITLE_KEY);
        String content = (String) params.get(Params.POST_REQUEST_CONTENT_KEY);
        String shortDesc = (String) params.get(Params.POST_REQUEST_DESC_KEY);

        String imageUrl = uploadImage(image);

        return postService.save(id, imageUrl, content, shortDesc, title);
    }

    private String uploadImage(MultipartFile image) throws IOException {
        String filename = Paths.get(image.getOriginalFilename()).getFileName().toString();

        File file = new File(FILE_PATH_PREFIX + File.separator + filename.hashCode() % DIRECTORIES_COUNT +
                File.separator + filename);

        InputStream content = image.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        out.write(buffer);
        out.close();

        return cloudinary.uploader().upload(file, new HashMap<>()).get("secure_url").toString();
    }
}
