package pl.yahoo.pawelpiedel.cms.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    public static final int MIN_CHARS_IN_PASSWORD = 6;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = MIN_CHARS_IN_PASSWORD)
    private String password;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]*")
    private String firstName;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]*")
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
