package pl.kluczify.lock.srvices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kluczify.lock.dao.UserDao;
import pl.kluczify.lock.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public void addUser(long id, LocalDateTime startDate, LocalDateTime expirationDate, ArrayList<String> roomList) {
		userDao.save(new User( id, startDate, expirationDate, roomList));
	}

}
