package pl.kluczify.client.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name="Permission")
public class Permission {

    @Id
    @NotNull
    private long id;

    @NotNull
    private Date startDate;

    @NotNull
    private Date expirationDate;

    @NotNull
    private String roomNumber;

    @NotNull
    private String tokenToOpen;

    public Permission(long id, Date startDate, Date expirationDate, String roomNumber, String tokenToOpen) {
        this.id = id;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.roomNumber = roomNumber;
        this.tokenToOpen = tokenToOpen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Permission() {
    }
}
