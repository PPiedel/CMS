package pl.yahoo.pawelpiedel.cms.services.user;

import pl.yahoo.pawelpiedel.cms.dto.UserDto;
import pl.yahoo.pawelpiedel.cms.model.User;


public interface UserService {
    User registerNewUser(UserDto userDto) throws EmailExistsException;

    User findUserByEmail(String email);
}
