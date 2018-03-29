package pl.yahoo.pawelpiedel.cms.services.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.yahoo.pawelpiedel.cms.dto.PostDto;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.repositories.PostRepository;

import java.sql.Date;
import java.util.List;


@Component
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findPostsByAuthor(String email) {
        return postRepository.findByAuthor_Email(email);
    }


    @Override
    public Post findPostById(Long id) {
        return postRepository.findOne(id);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(id);
    }

    @Override
    public Post addPost(PostDto postDto, User author) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setAuthor(author);
        post.setContent(postDto.getContent());
        post.setPosterPath(postDto.getBackdropPath());
        java.util.Date current = new java.util.Date();
        post.setDate(new Date(current.getTime()));
        return postRepository.save(post);
    }

    @Override
    public Post editPost(PostDto newPostDto) {
        Post oldPost = postRepository.findOne(newPostDto.getId());
        if (oldPost != null) {
            oldPost.setTitle(newPostDto.getTitle());
            oldPost.setContent(newPostDto.getContent());
            oldPost.setPosterPath(newPostDto.getBackdropPath());
            postRepository.save(oldPost);
        }
        return oldPost;
    }
}
