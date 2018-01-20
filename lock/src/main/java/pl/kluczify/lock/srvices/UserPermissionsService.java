package pl.kluczify.lock.srvices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kluczify.lock.dao.LockDao;
import pl.kluczify.lock.dao.UserPermissionDao;
import pl.kluczify.lock.models.Lock;
import pl.kluczify.lock.models.UserPermission;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserPermissionsService {
	private final String USER_AGENT = "Mozilla/5.0";

	@Autowired
	UserPermissionDao userDao;

	@Autowired
	LockDao lockDao;

	private String[] locksAddresses = {
			"localhost:2147",
			"localhost:2157",
			"localhost:2167",
			"localhost:2177",
	};

	public void addUserPermissions(long id, LocalDateTime startDate, LocalDateTime expirationDate, String roomNumber, String tokenToOpen) {
		userDao.save(new UserPermission(id, startDate, expirationDate, roomNumber, tokenToOpen));
	}

	public void addLock(long id, LocalDateTime lastOpenDateTime, Boolean isOpen, ArrayList<UserPermission> userPermissionsList, String roomNumber, String roomType, String roomLocation) {
		lockDao.save(new Lock(id, lastOpenDateTime, isOpen, userPermissionsList, roomNumber, roomType, roomLocation));
	}

	public UserPermission getPerm(long id) {
		return userDao.findOne(id);
	}

	public Lock getLock(long id) {
		return lockDao.findOne(id);
	}

	private String sendGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		StringBuffer response = new StringBuffer();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		return response.toString();
	}

	private String sendPost(String url, String urlParameters) throws Exception {

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();
	}

	public void openLock(Long id, String token, String roomNumber, LocalDateTime openDateTime) {
		Lock lockConfig = lockDao.findOne(0L);

		if (!lockConfig.getOpen()) {
			List<UserPermission> userPermissionList;
			if ((userPermissionList = lockConfig.getUserPermissionsList()) != null) {
				for (UserPermission up : userPermissionList) {
					if (up.canRuleLock(id, token, roomNumber, openDateTime)) {
						if(checkOtherLocks(id, token, roomNumber, openDateTime)) {
							System.out.println(" ---------------------- USER " + id + " OPENING LOCK ---------------------- ");
						}
					}
				}
			}
		} else {
			System.out.println(" ---------------------- LOCK ALREADY OPENED ---------------------- ");
		}
	}

	public void closeLock(Long id, String token, String roomNumber, LocalDateTime openDateTime) {
		Lock lockConfig = lockDao.findOne(0L);

		if (lockConfig.getOpen()) {
			List<UserPermission> userPermissionList;
			if ((userPermissionList = lockConfig.getUserPermissionsList()) != null) {
				for (UserPermission up : userPermissionList) {
					if (up.canRuleLock(id, token, roomNumber, openDateTime)) {
						if(checkOtherLocks(id, token, roomNumber, openDateTime)) {
							System.out.println(" ---------------------- USER " + id + " CLOSING LOCK ---------------------- ");
						}
					}
				}
			}
		} else {
			System.out.println(" ---------------------- LOCK ALREADY CLOSED ---------------------- ");
		}
	}

	private boolean checkOtherLocks(Long id, String token, String roomNumber, LocalDateTime openDateTime) {
		Random r = new Random();
		int firstLock = r.nextInt(4);
		int secondLock = r.nextInt(4);

		if (secondLock == firstLock) {
			secondLock = (secondLock + 1) % 4;
		}

		String urlParameters = "id=" + id + "&token=" + token + "&roomNumber=" + roomNumber + "&openDateTime=" + openDateTime.toString();
		try {
			boolean firstLockFlag = Boolean.valueOf(sendGet(locksAddresses[firstLock] + "/check?" + urlParameters));
			boolean secondLockFlag = Boolean.valueOf(sendGet(locksAddresses[secondLock] + "/check?" + urlParameters));

			return (firstLockFlag || secondLockFlag);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkUserPermission(Long id, String token, String roomNumber, LocalDateTime openDateTime) {
		Lock lockConfig = lockDao.findOne(0L);

		List<UserPermission> userPermissionList;
		if ((userPermissionList = lockConfig.getUserPermissionsList()) != null) {
			for (UserPermission up : userPermissionList) {
				if (up.canRuleLock(id, token, roomNumber, openDateTime)) {
					return true;
				}
			}
		}
		return false;
	}

}