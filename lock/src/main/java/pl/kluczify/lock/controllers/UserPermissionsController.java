package pl.kluczify.lock.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kluczify.lock.models.Lock;
import pl.kluczify.lock.models.UserPermission;
import pl.kluczify.lock.srvices.UserPermissionsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserPermissionsController {

	@Autowired
	private UserPermissionsService userPermissionsService;

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("/updateUserPermissions")
	@ResponseBody
	public void updatePermissionFromServer(JSONObject json) {
		try {
			Long id = json.getLong("id");
			LocalDateTime startDate = LocalDateTime.parse(json.getString("startTime"), dateTimeFormatter);
			LocalDateTime expirationDate = LocalDateTime.parse(json.getString("expirationDate"), dateTimeFormatter);
			String roomNumber = json.getString("roomNumber");
			String tokenToOpen = json.getString("token");

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
	public void updateLock(JSONObject json) {
		try {
			Long id = json.getLong("id");
			LocalDateTime lastOpenDateTime = LocalDateTime.parse(json.getString("lastOpenDateTime"), dateTimeFormatter);
			Boolean isOpen = json.getBoolean("isOpen");

			ArrayList<UserPermission> userPermissionsList = new ArrayList<>();
			JSONArray jsonArray = json.getJSONArray("userPermissionsList");
			for (int i = 0; i < jsonArray.length(); i++) {
				userPermissionsList.add(createUserPermissionFromJSON(jsonArray.getJSONObject(i)));
			}

			String roomNumber = json.getString("roomNumber");
			String roomType = json.getString("roomType");
			String roomLocation = json.getString("roomLocation");
			Lock userPermission = userPermissionsService.getLock(id);
			if ((userPermission == null)) {
				userPermissionsService.addLock(
						id,
						lastOpenDateTime,
						isOpen,
						userPermissionsList,
						roomNumber,
						roomType,
						roomLocation
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserPermission createUserPermissionFromJSON(JSONObject json) throws JSONException {
		long id = json.getLong("id");
		LocalDateTime startDate = LocalDateTime.parse(json.getString("startDate"), dateTimeFormatter);
		LocalDateTime expirationDate = LocalDateTime.parse(json.getString("expirationDate"), dateTimeFormatter);
		String roomNumber = json.getString("roomNumber");
		String tokenToOpen = json.getString("tokenToOpen");

		return new UserPermission(id, startDate, expirationDate, roomNumber, tokenToOpen);
	}

	// Parametry od klienta o klienta(Long id, String token, roomNumber,Date openDateTime)

	@RequestMapping("/open")
	@ResponseBody
	public void tryToOpen(JSONObject json) {
		try {
			Long id = json.getLong("id");
			String token = json.getString("token");
			String roomNumber = json.getString("roomNumber");
			LocalDateTime openDateTime = LocalDateTime.parse(json.getString("openDateTime"), dateTimeFormatter);

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
			LocalDateTime openDateTime = LocalDateTime.parse(json.getString("openDateTime"), dateTimeFormatter);

			userPermissionsService.closeLock(id, token, roomNumber, openDateTime);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/check")
	@ResponseBody
	public boolean checkPermission(JSONObject json) {
		try {
			Long id = json.getLong("id");
			String token = json.getString("token");
			String roomNumber = json.getString("roomNumber");
			LocalDateTime openDateTime = LocalDateTime.parse(json.getString("openDateTime"), dateTimeFormatter);

			return userPermissionsService.checkUserPermission(id, token, roomNumber, openDateTime);

		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

}
