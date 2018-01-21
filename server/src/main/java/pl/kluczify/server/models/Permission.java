package pl.kluczify.server.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jedrek on 17.12.17.
 */
@Entity
@Table(name = "permissions")
public class Permission {

    // Private fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn (name="user_id",referencedColumnName="id",nullable=false,unique=false)
    private User user;


    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private LocalDateTime expiryDate;

    @JoinTable
    @OneToMany
    private List<Room> rooms;

    // Public methods

    protected Permission() { }

    public Permission(long id) {
        this.id = id;
    }

    public Permission(User user, LocalDateTime creationDate, LocalDateTime expiryDate, Room room) {
        rooms = new ArrayList<>();
        this.user = user;
        this.creationDate = creationDate;
        this.expiryDate = expiryDate;
        this.rooms.add(room);
    }

    // Getter and setter methods

    public long getId() {
        return id;
    }
    public void setId(long value) {
        this.id = value;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime value) {
        this.creationDate = value;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDateTime value) {
        this.expiryDate = value;
    }

    public List<Room> getRooms(){
        return rooms;
    }
    public void setRooms(List<Room> rooms){
        this.rooms = rooms;
    }

    public List<Room> addRoom(Room room){
        this.rooms.add(room);
        return rooms;
    }
}