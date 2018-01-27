package pl.yahoo.pawelpiedel.cms.services;

public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}