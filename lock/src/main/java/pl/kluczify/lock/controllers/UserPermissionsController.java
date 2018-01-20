package pl.kluczify.lock.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kluczify.lock.models.Lock;
import pl.kluczify.lock.models.UserPermission;
import pl.kluczify.lock.srvices.UserPermissionsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class UserPermissionsController {

    @Autowired
    UserPermissionsService userPermissionsService;


    @RequestMapping("/updateUserPermissions")
    @ResponseBody
    public void updatePermissionFromServer(JSONObject param) {
        Long id = null;
        try {
            id = (Long) param.getLong("id");
            LocalDateTime startDate = null , expirationDate = null;
            String roomNumber = null;
            String tokenToOpen = null;
            UserPermission userPermission = userPermissionsService.getPerm(id);
            if ((userPermission == null)) {
                userPermissionsService.addUserPermissions(id, startDate, expirationDate, roomNumber, tokenToOpen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateLock")
    @ResponseBody
    public void updateLock(JSONObject obj) {
        try {
            Long id = null;
            LocalDateTime lastOpenDateTime= null;
            Boolean isOpen= null;
            ArrayList< UserPermission > userPermissionsList= null;
            String roomNumber= null;
            String roomType= null;
            String roomLocation= null;
            Lock userPermission = userPermissionsService.getLock(id);
            if ((userPermission == null)) {
                userPermissionsService.addLock(  id,  lastOpenDateTime,
                isOpen,    userPermissionsList,  roomNumber,  roomType,
                roomLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Parametry od klienta o klienta(Long id, String token, roomNumber,Date openDateTime)

    @RequestMapping("/open")
    @ResponseBody
    public void tryToOpen(JSONObject json) {
        try {
            Long id = json.getLong("id");
            String token = json.getString("token");
            String roomNumber = json.getString("roomNumber");
            LocalDateTime openDateTime = LocalDateTime.parse(json.getString("openDateTime"));

            userPermissionsService.openLock(id, token, roomNumber, openDateTime);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/close")
    @ResponseBody
    public void tryToClose(JSONObject json) {
        try {
            Long id = json.getLong("id");
            String token = json.getString("token");
            String roomNumber = json.getString("roomNumber");
            LocalDateTime openDateTime = LocalDateTime.parse(json.getString("openDateTime"));

            userPermissionsService.closeLock(id, token, roomNumber, openDateTime);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

	@RequestMapping("/check")
	@ResponseBody
	public void checkPermission(JSONObject json) {
		try {
			Long id = json.getLong("id");
			String roomNumber = json.getString("roomNumber");
			LocalDateTime openDateTime = LocalDateTime.parse(json.getString("openDateTime"));

			userPermissionsService.checkUserPermission(id, roomNumber, openDateTime);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


}
