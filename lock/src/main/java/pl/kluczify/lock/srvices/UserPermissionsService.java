package pl.kluczify.lock.srvices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kluczify.lock.dao.UserDao;
import pl.kluczify.lock.models.UserPermission;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserPermissionsService {

	@Autowired
	UserDao userDao;

	public void addUserPermissions(long id, LocalDateTime startDate, LocalDateTime expirationDate, ArrayList<String> roomList, LocalDateTime timeStamp) {
		userDao.save(new UserPermission( id, startDate, expirationDate, roomList, timeStamp));
	}

	public UserPermission get(long id) {
		return userDao.findOne(id);
	}



}
