package pl.kluczify.client.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    private long id;

    private ArrayList<Permission> userPermissionsList;

    public long getId() {
        return id;
    }

    public ArrayList<Permission> getUserPermissionsList() {
        return userPermissionsList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserPermissionsList(ArrayList<Permission> userPermissionsList) {
        this.userPermissionsList = userPermissionsList;
    }

    public Client() {
    }

    public Client(Long id) {
        this.id = id;
    }

    public Client(long id, ArrayList<Permission> userPermissionsList) {
        this.id = id;
        this.userPermissionsList = userPermissionsList;
    }
}


