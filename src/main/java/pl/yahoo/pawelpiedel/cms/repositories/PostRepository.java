package pl.yahoo.pawelpiedel.cms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yahoo.pawelpiedel.cms.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();

    List<Post> findByAuthor_Email(String email);
}
