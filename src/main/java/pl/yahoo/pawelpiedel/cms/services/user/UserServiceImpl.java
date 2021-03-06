package pl.yahoo.pawelpiedel.cms.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.yahoo.pawelpiedel.cms.dto.UserDto;
import pl.yahoo.pawelpiedel.cms.model.Role;
import pl.yahoo.pawelpiedel.cms.model.Roles;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.repositories.RoleRepository;
import pl.yahoo.pawelpiedel.cms.repositories.UserRepository;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerNewUser(UserDto userDto) throws EmailExistsException {
        User user;

        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address: "
                            + userDto.getEmail());
        } else {
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            Role userRole = roleRepository.findByName(Roles.ROLE_USER.toString());
            // System.out.println(userRole.toString());
            user.setRoles(Collections.singletonList(userRole));
        }

        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
