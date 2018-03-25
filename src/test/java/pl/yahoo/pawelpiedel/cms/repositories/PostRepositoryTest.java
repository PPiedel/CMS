package pl.yahoo.pawelpiedel.cms.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.yahoo.pawelpiedel.cms.model.Post;
import pl.yahoo.pawelpiedel.cms.model.User;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PostRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PostRepository postRepository;

    @Test
    public void findAllShouldReturnPersistedPosts() {
        //given
        String email = "testEmail@gmail.com";
        String firstName = "testName";
        String lastName = "testLastName";
        User testUser = new User();
        testUser.setEmail(email);
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setEmail(email);

        testEntityManager.persist(testUser);
        testEntityManager.flush();

        for (int i = 0; i < 10; i++) {
            testEntityManager.persist(new Post("test_title", "test_content", testUser));
        }
        testEntityManager.flush();

        //when
        List<Post> allPosts = postRepository.findAll();

        //then
        assertEquals(10, allPosts.size());
    }

    @Test
    public void findAllShouldReturnEmptyList() {
        //given nothing

        //when
        //when
        List<Post> allPosts = postRepository.findAll();

        //then
        assertTrue(allPosts.isEmpty());
    }

    @Test
    public void findByAuthor_EmailShouldReturnPosts() {
        //given
        String email = "testEmail@gmail.com";
        String firstName = "testName";
        String lastName = "testLastName";
        User testUser = new User();
        testUser.setEmail(email);
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setEmail(email);

        testEntityManager.persist(testUser);

        Post dummy = new Post();
        dummy.setTitle("test_title");
        dummy.setContent("test_content");
        dummy.setAuthor(testUser);

        testEntityManager.persist(dummy);
        testEntityManager.flush();

        //when
        java.util.List<Post> authorsPosts = postRepository.findByAuthor_Email(email);

        //then
        assertEquals(1, authorsPosts.size());
    }

    @Test
    public void findByAuthoEmailShouldReturnEmptyList() {
        //given
        String email = "testEmail@gmail.com";
        String firstName = "testName";
        String lastName = "testLastName";
        User testUser = new User();
        testUser.setEmail(email);
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setEmail(email);

        testEntityManager.persist(testUser);
        testEntityManager.flush();

        //when
        List<Post> userPosts = postRepository.findByAuthor_Email(email);


        //then
        assertTrue(userPosts.isEmpty());

    }

    @Test
    public void findByAuthoEmailShouldReturnEmptyListWhenThereIsNoUserWithSearchedEmail() {
        //given
        String email = "testEmail@gmail.com";

        //when
        List<Post> userPosts = postRepository.findByAuthor_Email(email);

        //then
        assertTrue(userPosts.isEmpty());

    }
}