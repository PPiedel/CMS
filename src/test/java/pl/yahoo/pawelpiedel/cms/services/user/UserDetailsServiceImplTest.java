package pl.yahoo.pawelpiedel.cms.services.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.yahoo.pawelpiedel.cms.model.Role;
import pl.yahoo.pawelpiedel.cms.model.Roles;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.repositories.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsService userDetailsService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void loadUserByUsername() {
        //given
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        String email = "test@gmail.com";
        user.setEmail(email);
        java.util.List<Role> roles = Collections.singletonList(new Role(Roles.ROLE_USER.toString()));
        user.setRoles(roles);
        String password = "xxxzzzyyy";
        user.setPassword(password);
        when(userRepository.findByEmail(email)).thenReturn(user);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(roles.get(0).getName()));

        //then
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        //then
        assertEquals(email, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertArrayEquals(grantedAuthorities.toArray(), userDetails.getAuthorities().toArray());
    }

    @TestConfiguration
    static class UserDetailsServiceImplTestConfiguration {
        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsServiceImpl();
        }
    }
}