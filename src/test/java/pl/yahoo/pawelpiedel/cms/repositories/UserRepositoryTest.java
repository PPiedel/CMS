package pl.yahoo.pawelpiedel.cms.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.yahoo.pawelpiedel.cms.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmailShouldReturnUser() {
        //given
        String email = "testEmail@gmail.com";
        String firstName = "testName";
        String lastName = "testLastName";
        User testUser = new User();
        testUser.setEmail(email);
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);

        testEntityManager.persist(testUser);
        testEntityManager.flush();

        //when
        User found = userRepository.findByEmail(email);

        assertEquals(testUser.getEmail(), found.getEmail());

    }

    @Test
    public void findByEmailShouldReturnNull() {
        //given
        String email = "testEmail@gmail.com";

        //when
        User found = userRepository.findByEmail(email);

        //then
        assertNull(found);
    }
}