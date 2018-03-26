package pl.yahoo.pawelpiedel.cms.services.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.yahoo.pawelpiedel.cms.dto.UserDto;
import pl.yahoo.pawelpiedel.cms.model.Role;
import pl.yahoo.pawelpiedel.cms.model.Roles;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.repositories.RoleRepository;
import pl.yahoo.pawelpiedel.cms.repositories.UserRepository;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserServiceImplTest {
    @MockBean
    UserRepository userRepository;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    RoleRepository roleRepository;

    @Mock
    User userMock;

    @Autowired
    UserService userService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerNewUser() {
        //given
        UserDto userDto = new UserDto();
        String email = "test@gmail.com";
        userDto.setEmail(email);
        String firstName = "testFirstName";
        userDto.setFirstName(firstName);
        String lastName = "testLastName";
        userDto.setLastName(lastName);
        String password = "testPassword";
        userDto.setPassword(password);
        when(userRepository.findByEmail(email)).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(roleRepository.findByName(Roles.ROLE_USER.toString())).thenReturn(new Role(Roles.ROLE_USER.toString()));
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(password);

        //when
        User registered = null;
        try {
            registered = userService.registerNewUser(userDto);
        } catch (EmailExistsException e) {
            e.printStackTrace();
        }

        //then
        assertNotNull(registered);
    }

    @Test
    public void registerNewUserShouldReturnNullBecauseOfExistingEmailException() {
        //given
        UserDto userDto = new UserDto();
        String email = "test@gmail.com";
        userDto.setEmail(email);
        String firstName = "testFirstName";
        userDto.setFirstName(firstName);
        String lastName = "testLastName";
        userDto.setLastName(lastName);
        String password = "testPassword";
        userDto.setPassword(password);
        User alreadyRegistered = new User();
        when(userRepository.findByEmail(email)).thenReturn(alreadyRegistered);

        //when
        User notRegistered = null;
        try {
            userService.registerNewUser(userDto);
        } catch (EmailExistsException e) {
            e.printStackTrace();
        }

        //then
        assertNull(notRegistered);

    }

    @Test
    public void findUserByEmail() {
        //given
        String email = "test@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(userMock);

        //when
        User founded = userService.findUserByEmail(email);

        //then
        assertEquals(userMock, founded);

    }

    @TestConfiguration
    static class UserServiceImplTestConfiguration {

        @Bean
        public UserService userService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
            return new UserServiceImpl(userRepository, roleRepository, bCryptPasswordEncoder);
        }
    }
}