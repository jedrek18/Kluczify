package pl.kluczify.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jedrek on 17.12.17.
 */
@Entity
@Table(name = "rooms")
public class Room {

    // Private fields

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String roomNumber;

    @NotNull
    private String roomType;

    @NotNull
    private String roomLocation;

    // Public methods

    public Room() { }

    public Room(long id) {
        this.id = id;
    }

    public Room(String roomNumber, String roomType, String roomLocation) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomLocation = roomLocation;
    }

    // Getter and setter methods
    public long getId() {
        return id;
    }
    public void setId(long value) {
        this.id = value;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String value) {
        this.roomType = value;
    }

    public String getRoomLocation() {
        return roomLocation;
    }
    public void setRoomLocation(String value) {
        this.roomLocation = value;
    }

}

