package pl.kluczify.lock.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users_permissions")
public class UserPermission {

	@Id
	@NotNull
	private long id;

	@NotNull
	private LocalDateTime startDate;

	@NotNull
	private LocalDateTime expirationDate;

	@NotNull
	private ArrayList<String> roomList;

	@NotNull
	private LocalDateTime timeStamp;

	public UserPermission(long id, LocalDateTime startDate, LocalDateTime expirationDate, ArrayList<String> roomList, LocalDateTime timeStamp) {
		this.id = id;
		this.startDate = startDate;
		this.expirationDate = expirationDate;
		this.roomList = roomList;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public List<String> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<String> roomList) {
		this.roomList = roomList;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}
