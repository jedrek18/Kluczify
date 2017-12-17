package pl.kluczify.lock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kluczify.lock.models.UserPermission;
import pl.kluczify.lock.srvices.UserPermissionsService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class UserPermissionsController {

	@Autowired
	UserPermissionsService userPermissionsService;

	@RequestMapping("/update")
	@ResponseBody
	public void createFromServer(long id, LocalDateTime startDate, LocalDateTime expirationDate, ArrayList<String> roomList, LocalDateTime timeStamp) {
		UserPermission userPermission = userPermissionsService.get(id);
		if((userPermission == null) || timeStamp.isAfter(userPermission.getTimeStamp())) {
				userPermissionsService.addUserPermissions(id, startDate, expirationDate, roomList, timeStamp);
		}
	}

}
