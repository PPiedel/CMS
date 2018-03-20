package pl.yahoo.pawelpiedel.cms.services.post;

import pl.yahoo.pawelpiedel.cms.dto.PostDto;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.User;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    List<Post> findPostsByAuthor(String userEmail);

    Post findPostById(Long id);

    void deletePost(Long id);

    void addPost(PostDto postDto, User author);

    void editPost(PostDto post);

}
