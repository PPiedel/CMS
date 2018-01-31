package pl.yahoo.pawelpiedel.cms.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.services.auth.AuthService;
import pl.yahoo.pawelpiedel.cms.services.post.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthService authService;

    @GetMapping("/posts")
    public List<Post> posts() {
        return postService.findAll();
    }


    @GetMapping("/user/posts")
    public List<Post> userPosts() {
        List<Post> userPosts = new ArrayList<>();
        String username = authService.getCurrentUserName();
        if (username != null) {
            userPosts = postService.findPostsByAuthor(username);
        }
        return userPosts;
    }

}
