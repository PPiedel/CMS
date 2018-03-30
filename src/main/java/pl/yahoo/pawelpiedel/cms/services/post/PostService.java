package pl.yahoo.pawelpiedel.cms.services.post;

import org.springframework.stereotype.Service;
import pl.yahoo.pawelpiedel.cms.dto.PostDto;
import pl.yahoo.pawelpiedel.cms.model.Category;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.User;

import java.util.List;

@Service
public interface PostService {
    List<Post> findAll();

    List<Post> findPostsByAuthor(String userEmail);

    List<Post> findPostsByCategory(Category category);

    Post findPostById(Long id);

    void deletePost(Long id);

    Post addPost(PostDto postDto, User author);

    Post editPost(PostDto post);

}
