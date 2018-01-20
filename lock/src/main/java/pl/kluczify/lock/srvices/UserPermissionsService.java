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

@Service
public class UserPermissionsService {
    private final String USER_AGENT = "Mozilla/5.0";

    @Autowired
    UserPermissionDao userDao;

    @Autowired
    LockDao lockDao;

    public void addUserPermissions(long id, LocalDateTime startDate, LocalDateTime expirationDate, String roomNumber, String tokenToOpen) {
        userDao.save(new UserPermission(id, startDate, expirationDate, roomNumber,tokenToOpen));
    }

    public void addLock(long id, LocalDateTime lastOpenDateTime, Boolean isOpen, ArrayList<UserPermission> userPermissionsList, String roomNumber, String roomType, String roomLocation){
        lockDao.save(new Lock(id,lastOpenDateTime,isOpen,userPermissionsList,roomNumber,roomType,roomLocation));
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
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
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

}
