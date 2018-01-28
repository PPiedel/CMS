package pl.yahoo.pawelpiedel.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.yahoo.pawelpiedel.cms.model.Category;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.User;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    List<Post> findByAuthor(User author);

    List<Post> findByCategory(Category category);

    List<Post> findById(long id);
}
