package pl.yahoo.pawelpiedel.cms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.yahoo.pawelpiedel.cms.model.Category;
import pl.yahoo.pawelpiedel.cms.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();

    List<Post> findByAuthor_Email(String email);

    List<Post> findByCategory(Category category);

    List<Post> findById(long id);
}
