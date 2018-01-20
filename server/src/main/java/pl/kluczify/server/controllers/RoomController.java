package pl.kluczify.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kluczify.server.models.Room;
import pl.kluczify.server.models.RoomDao;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jedrek on 17.12.17.
 */
@Controller
public class RoomController {
    private final String USER_AGENT = "Mozilla/5.0";

    @Autowired
    private RoomDao roomDao;

    //Public methods

    @RequestMapping("/create-room")
    @ResponseBody
    public String createRoom(String roomNumber, String roomType, String roomLocation) {
        Room room = null;
        try {
            room = new Room(roomNumber, roomType, roomLocation);

            roomDao.save(room);
        } catch (Exception ex) {
            return "Error creating the room: " + ex.toString();
        }
        return "Room succesfully created! (id = " + room.getId() + ")";
    }

    @RequestMapping("/delete-room")
    @ResponseBody
    public String deleteRoom(long id) {
        try {
            Room room = new Room(id);
            roomDao.delete(room);
        } catch (Exception ex) {
            return "Error deleting the room: " + ex.toString();
        }
        return "Room succesfully deleted!";
    }


    @RequestMapping("/update-room")
    @ResponseBody
    public String updateRoom(long id, String roomNumber, String roomType, String roomLocation) {
        try {
            Room room = roomDao.findOne(id);
            room.setRoomNumber(roomNumber);
            room.setRoomNumber(roomType);
            room.setRoomLocation(roomLocation);
            roomDao.save(room);
        } catch (Exception ex) {
            return "Error updating the room: " + ex.toString();
        }
        return "Room succesfully updated!";
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
