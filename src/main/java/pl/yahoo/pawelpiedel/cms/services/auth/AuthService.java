package pl.yahoo.pawelpiedel.cms.services.auth;

import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication getAuthenthication();

    String getCurrentUserName();
}
