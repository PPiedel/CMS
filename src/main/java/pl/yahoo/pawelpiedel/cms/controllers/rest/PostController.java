package pl.yahoo.pawelpiedel.cms.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.services.post.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/posts")
    public List<Post> posts() {
        return postService.findAll();
    }


    @GetMapping("/user/posts")
    public List<Post> userPosts() {
        List<Post> userPosts = new ArrayList<>();
        String username = getCurrentUserName();
        if (username != null) {
            userPosts = postService.findPostsByAuthor(username);
        }
        return userPosts;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        } else {
            return null;
        }
    }
}
