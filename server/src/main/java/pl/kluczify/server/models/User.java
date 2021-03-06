package pl.kluczify.server.models;

import com.google.gson.annotations.SerializedName;
import pl.kluczify.server.utils.JsonConverter;
import pl.kluczify.server.utils.Jsonable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by jedrek on 17.12.17.
 */
@Entity
@Table(name = "users")
public class User implements Jsonable {

    // Private fields

    @SerializedName("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<Permission> permissions;

    @SerializedName("username")
    @NotNull
    private String userName;

    @SerializedName("name")
    @NotNull
    private String firstName;

    @SerializedName("lastName")
    @NotNull
    private String lastName;

    @SerializedName("email")
    @NotNull
    private String emailAddress;

    @SerializedName("password")
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
        return userName;
    }
    public void setUserName(String value) {
        this.userName = value;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String value) {
        this.password = value;
    }

    @Override
    public String toJson() {
        return JsonConverter.toJson(this);
    }

    public static User fromJson(String json) {
        return JsonConverter.fromJson(json, User.class);
    }


}

