package pl.yahoo.pawelpiedel.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.services.post.PostService;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/posts")
    public List<Post> posts() {
        return postService.findAllOrderedByDate();
    }
}
