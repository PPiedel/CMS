package pl.yahoo.pawelpiedel.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.yahoo.pawelpiedel.cms.dto.UserDto;
import pl.yahoo.pawelpiedel.cms.model.Role;
import pl.yahoo.pawelpiedel.cms.model.User;
import pl.yahoo.pawelpiedel.cms.repositories.UserRepository;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerNewUser(UserDto userDto) throws EmailExistsException {
        User user = null;
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress: "
                            + userDto.getEmail());
        } else {
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        }

        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
