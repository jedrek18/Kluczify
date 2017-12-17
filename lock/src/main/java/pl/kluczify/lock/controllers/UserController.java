package pl.kluczify.lock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kluczify.lock.srvices.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/create")
	@ResponseBody
	public void create(long id, LocalDateTime startDate, LocalDateTime expirationDate, List<String> roomList) {
		userService.addUser(id, startDate, expirationDate, roomList);
	}


}
