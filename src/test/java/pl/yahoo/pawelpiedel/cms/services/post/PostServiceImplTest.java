package pl.yahoo.pawelpiedel.cms.services.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.yahoo.pawelpiedel.cms.dto.PostDto;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.Role;
import pl.yahoo.pawelpiedel.cms.model.Roles;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.repositories.PostRepository;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PostServiceImplTest {

    @MockBean
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Mock
    Post postMock1;

    @Mock
    Post postMock2;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        //given
        java.util.List<Post> persistedPosts = Collections.singletonList(postMock1);
        when(postRepository.findAll()).thenReturn(persistedPosts);

        //when
        java.util.List<Post> allPosts = postService.findAll();

        //then
        assertArrayEquals(persistedPosts.toArray(), allPosts.toArray());
    }

    @Test
    public void findPostsByAuthor() {
        //given
        String email = "test@gmail.com";
        User author = new User();
        author.setEmail(email);
        java.util.List<Post> authorsPost = new ArrayList<Post>();
        authorsPost.add(postMock1);
        authorsPost.add(postMock2);
        when(postRepository.findByAuthor_Email(email)).thenReturn(authorsPost);

        //when
        java.util.List<Post> posts = postService.findPostsByAuthor(email);

        //then
        assertArrayEquals(authorsPost.toArray(), posts.toArray());

    }

    @Test
    public void findPostById() {
        //given
        Long id = 435435L;
        when(postRepository.findOne(id)).thenReturn(postMock1);

        //when
        Post found = postService.findPostById(id);

        //then
        assertEquals(postMock1, found);

    }

    @Test
    public void addPostShouldProcessCorrectly() {
        //given
        PostDto postDto = new PostDto();
        String title = "title";
        postDto.setTitle(title);
        String backDropPath = "backdropath";
        postDto.setBackdropPath(backDropPath);
        String content = "content";
        postDto.setContent(content);

        User author = new User();
        author.setEmail("testemai@gmail.com");
        author.setPassword("test");
        author.setFirstName("testName");
        author.setLastName("lastName");
        author.setRoles(Collections.singletonList(new Role(Roles.ROLE_USER.toString())));

        Post post = new Post(title, content, author);
        when(postRepository.save(any(Post.class))).thenReturn(post);

        //when
        Post added = postService.addPost(postDto, author);

        //then
        assertEquals(post.getContent(), added.getContent());
        assertEquals(post.getPosterPath(), added.getPosterPath());
        assertEquals(post.getTitle(), added.getTitle());
    }

    @Test
    public void editPost() {
    }

    @TestConfiguration
    static class PostServiceTestImplConfiguration {
        @Bean
        PostService postService(PostRepository postRepository) {
            return new PostServiceImpl(postRepository);
        }

    }
}