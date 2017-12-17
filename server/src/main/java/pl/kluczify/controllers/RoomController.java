package pl.kluczify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kluczify.models.Room;
import pl.kluczify.models.RoomDao;

/**
 * Created by jedrek on 17.12.17.
 */
@Controller
public class RoomController {

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
        }
        catch (Exception ex) {
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
        }
        catch (Exception ex) {
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
        }
        catch (Exception ex) {
            return "Error updating the room: " + ex.toString();
        }
        return "Room succesfully updated!";
    }

}
