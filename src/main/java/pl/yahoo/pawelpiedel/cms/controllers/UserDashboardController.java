package pl.yahoo.pawelpiedel.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.services.auth.AuthService;
import pl.yahoo.pawelpiedel.cms.services.post.PostService;

import java.util.List;


@Controller
public class UserDashboardController {
    @Autowired
    private PostService postService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPosts() {
        return "user";
    }

    @RequestMapping(value = "user/posts/delete/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") Long id) {
        String name = authService.getCurrentUserName();

        tryToDeletePost(id, name);

        return "user";
    }

    private void tryToDeletePost(@PathVariable("id") Long id, String name) {
        if (name != null) {
            List<Post> userPosts = postService.findPostsByAuthor(name);
            if (userPostsContainsPostWithId(userPosts,id)) {
                postService.deletePost(id);
            }
        }
    }

    private boolean userPostsContainsPostWithId(java.util.List<Post> userPosts, Long id){
        return userPosts.stream().anyMatch(post -> post.getId().equals(id));
    }

}
