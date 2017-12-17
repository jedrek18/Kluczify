package pl.kluczify.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jedrek on 17.12.17.
 */
@Entity
@Table(name = "users")
public class User {

    // Private fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String emailAddress;

    @NotNull
    private String password;

    // Public methods

    public User() { }

    public User(long id) {
        this.id = id;
    }

    public User(String userName, String firstName, String lastName, String emailAddress, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    // Getter and setter methods
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getEmail() {
        return emailAddress;
    }

    public void setEmail(String value) {
        this.emailAddress = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }
    public String getUserName() {
        return lastName;
    }

    public void setUserName(String value) {
        this.lastName = value;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

}

