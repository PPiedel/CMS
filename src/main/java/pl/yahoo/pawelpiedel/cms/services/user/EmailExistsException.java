package pl.yahoo.pawelpiedel.cms.services.user;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}