package pl.yahoo.pawelpiedel.cms.services.user;

public class UsernameExistException extends Exception {
    public UsernameExistException(String message) {
        super(message);
    }
}
