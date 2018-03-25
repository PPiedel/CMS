package pl.yahoo.pawelpiedel.cms.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.yahoo.pawelpiedel.cms.model.Role;
import pl.yahoo.pawelpiedel.cms.model.Roles;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RoleRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findByNameShouldReturnUserRole() {
        //given
        Role role = new Role();
        role.setName(Roles.ROLE_USER.toString());

        testEntityManager.persist(role);
        testEntityManager.flush();

        //when
        Role founded = roleRepository.findByName(Roles.ROLE_USER.toString());

        //then
        assertEquals(Roles.ROLE_USER.toString(), founded.getName());
    }
}