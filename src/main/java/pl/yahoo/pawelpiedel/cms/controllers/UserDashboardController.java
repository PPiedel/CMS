package pl.yahoo.pawelpiedel.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.yahoo.pawelpiedel.cms.services.post.PostService;

@Controller
public class UserDashboardController {
    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPosts() {
        return "user";
    }

    @RequestMapping(value = "user/posts/delete/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "user";
    }

}
