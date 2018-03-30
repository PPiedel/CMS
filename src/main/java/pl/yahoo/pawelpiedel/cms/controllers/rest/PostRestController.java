package pl.yahoo.pawelpiedel.cms.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.yahoo.pawelpiedel.cms.model.Category;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.services.auth.AuthService;
import pl.yahoo.pawelpiedel.cms.services.category.CategoryService;
import pl.yahoo.pawelpiedel.cms.services.post.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostRestController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthService authService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/posts")
    public List<Post> posts() {
        return postService.findAll();
    }

    @GetMapping("/posts/{category}")
    public List<Post> postsFromCategory(@PathVariable String category) {
        Category categoryFounded = categoryService.findCategoryByName(category);

        List<Post> posts = new ArrayList<>();
        if (categoryFounded != null) {
            posts = postService.findPostsByCategory(categoryFounded);
        }

        return posts;
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
