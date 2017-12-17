package pl.kluczify.server.models;

import com.google.gson.annotations.SerializedName;
import pl.kluczify.server.utils.JsonConverter;
import pl.kluczify.server.utils.Jsonable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jedrek on 17.12.17.
 */
@Entity
@Table(name = "rooms")
public class Room implements Jsonable{

    // Private fields
    @SerializedName("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @SerializedName("number")
    @NotNull
    private String roomNumber;

    @SerializedName("type")
    @NotNull
    private String roomType;

    @SerializedName("location")
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


    @Override
    public String toJson() {
        return JsonConverter.toJson(this);
    }

    public static Room fromJson(String json) {
        return JsonConverter.fromJson(json, Room.class);
    }

}

