package pl.yahoo.pawelpiedel.cms.services.post;

import pl.yahoo.pawelpiedel.cms.model.Category;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.User;

import java.util.List;

public interface PostService {
    List<Post> findAllOrderedByDate();

    List<Post> findPostsByAuthor(User user);

    List<Post> findPostsByCategory(Category category);

}
