package pl.yahoo.pawelpiedel.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.yahoo.pawelpiedel.cms.dto.PostDto;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.services.auth.AuthService;
import pl.yahoo.pawelpiedel.cms.services.post.PostService;
import pl.yahoo.pawelpiedel.cms.services.user.UserService;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserDashboardController {
    public static final String POST_ATTRIBUTE = "post";

    @Autowired
    private PostService postService;

    @Autowired
    private AuthService authService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPosts() {
        return "user";
    }

    @RequestMapping(value = "/user/posts", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("post") @Valid PostDto postDto) {

        tryToAddNewPost(postDto);

        return "user";
    }

    @RequestMapping(value = "user/posts/delete/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") Long id) {
        String name = authService.getCurrentUserName();

        tryToDeletePost(id, name);

        return "user";
    }

    @RequestMapping(value = "user/posts/edit/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") Long id, Model model) {
        Post editedPost = postService.findPostById(id);

        PostDto editedPostDto = getPostDtoFromPost(editedPost);

        model.addAttribute(POST_ATTRIBUTE, editedPostDto);
        return "editPost";
    }

    private PostDto getPostDtoFromPost(Post post) {
        PostDto editedPostDto = new PostDto();
        editedPostDto.setId(post.getId());
        editedPostDto.setContent(post.getContent());
        editedPostDto.setBackdropPath(post.getPosterPath());
        editedPostDto.setTitle(post.getTitle());
        return editedPostDto;
    }

    @RequestMapping(value = "/user/posts/edit",method = RequestMethod.POST)
    public String replaceEditedPost(PostDto newPostDto){
        System.out.println(newPostDto);

        postService.editPost(newPostDto);

        return "user";
    }

    private void tryToAddNewPost(PostDto newPostDto) {
        String name = authService.getCurrentUserName();
        if (name != null) {
            User user = userService.findUserByEmail(name);
            postService.addPost(newPostDto, user);
        }
    }

    @RequestMapping(value = "user/posts/add", method = RequestMethod.GET)
    public String addPost(Model model) {
        model.addAttribute(POST_ATTRIBUTE, new PostDto());
        return "addPost";
    }

    private void tryToDeletePost(@PathVariable("id") Long id, String name) {
        if (name != null) {
            List<Post> userPosts = postService.findPostsByAuthor(name);
            if (userPostsContainsPostWithId(userPosts, id)) {
                postService.deletePost(id);
            }
        }
    }

    private boolean userPostsContainsPostWithId(java.util.List<Post> userPosts, Long id) {
        return userPosts.stream().anyMatch(post -> post.getId().equals(id));
    }

}
