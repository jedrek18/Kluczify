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
	private String roomNumber;

	@NotNull
	private String tokenToOpen;

	public UserPermission(long id, LocalDateTime startDate, LocalDateTime expirationDate, String roomNumber, String tokenToOpen) {
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

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
}
