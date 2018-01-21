package pl.kluczify.lock.models;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lock")
public class Lock {

	@Id
	@NotNull
	private long id;

	@NotNull
	private LocalDateTime lastOpenDateTime;

	@NotNull
	private Boolean isOpen;

	private ArrayList<UserPermission> userPermissionsList;

	@NotNull
	private String roomNumber;

	private String roomType;

	private String roomLocation;

	public long getId() {
		return id;
	}

	public LocalDateTime getLastOpenDateTime() {
		return lastOpenDateTime;
	}

	public Boolean getOpen() {
		return isOpen;
	}

	public ArrayList<UserPermission> getUserPermissionsList() {
		return userPermissionsList;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastOpenDateTime(LocalDateTime lastOpenDateTime) {
		this.lastOpenDateTime = lastOpenDateTime;
	}

	public void setOpen(Boolean open) {
		isOpen = open;
	}

	public void setUserPermissionsList(ArrayList<UserPermission> userPermissionsList) {
		this.userPermissionsList = userPermissionsList;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Lock() {
	}

	public String getRoomType() {
		return roomType;
	}

	public String getRoomLocation() {
		return roomLocation;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}

	public Lock(long id, LocalDateTime lastOpenDateTime, Boolean isOpen, ArrayList<UserPermission> userPermissionsList, String roomNumber, String roomType, String roomLocation) {
		this.id = id;
		this.lastOpenDateTime = lastOpenDateTime;
		this.isOpen = isOpen;
		this.userPermissionsList = userPermissionsList;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomLocation = roomLocation;
	}
}
