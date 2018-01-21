package pl.kluczify.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kluczify.server.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoomDao roomDao;
    //Public methods

    @RequestMapping("/create-permission")
    @ResponseBody
    public String create(Long userId, String creationDate, String expiryDate, Long roomId) {
        Permission permission = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        User user = userDao.findOne(userId);
        Room room = roomDao.findOne(roomId);
        LocalDateTime creationDateTime = LocalDateTime.parse(creationDate, formatter);
        LocalDateTime expiryDateTime = LocalDateTime.parse(expiryDate, formatter);
        try {
            permission = new Permission(user, creationDateTime, expiryDateTime, room);
            permissionDao.save(permission);
        }
        catch (Exception ex) {
            return "Error creating permission: " + ex.toString();
        }
        return "Permission succesfully created! (id = " + permission.getId() + ")";
    }

    @RequestMapping("/delete-permission")
    @ResponseBody
    public String delete(long id) {
        try {
            Permission permission = new Permission(id);
            permissionDao.delete(permission);
        }
        catch (Exception ex) {
            return "Error deleting permission: " + ex.toString();
        }
        return "Permission succesfully deleted!";
    }

    @RequestMapping("/get-permission-by-id")
    @ResponseBody
    public String getById(long id) {
        LocalDateTime permissionCreationDate;
        LocalDateTime permissionExpiryDate;
        List<Room> permissionRooms;
        User permissionUser;
        try {
            Permission permission = permissionDao.findById(id);
            permissionCreationDate = permission.getCreationDate();
            permissionExpiryDate = permission.getExpiryDate();
            permissionRooms = permission.getRooms();
            permissionUser = permission.getUser();
        }
        catch (Exception ex) {
            return "Permission not found";
        }
        return "Data for permission id=" + id + ": user: " + permissionUser.getUserName() + " (id=" + permissionUser.getId() + "), creation_date: " + permissionCreationDate + ", expiry_date: " + permissionExpiryDate + ", rooms: " + Arrays.toString(permissionRooms.toArray());
    }

    @RequestMapping("/update-permission-date")
    @ResponseBody
    public String updatePermission(long id,
                                   @RequestParam(value = "creationDate", required = false) String creationDate,
                                   @RequestParam (value = "expiryDate", required = false ) String expiryDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Permission permission = permissionDao.findOne(id);
            if(creationDate != null ){
                LocalDateTime creationDateTime = LocalDateTime.parse(creationDate, formatter);
                permission.setCreationDate(creationDateTime);
            }
            if(expiryDate != null ){
                LocalDateTime expiryDateTime = LocalDateTime.parse(expiryDate, formatter);

                permission.setExpiryDate(expiryDateTime);
            }
            permissionDao.save(permission);
        }
        catch (Exception ex) {
            return "Error updating permission: " + ex.toString();
        }
        return "Permission succesfully updated!";
    }

    @RequestMapping("/add-room-to-permission")
    @ResponseBody
    public String addRoom(long permissionId, long roomId){
        List<Room> allRooms;
        try {
            Permission permission = permissionDao.findOne(permissionId);
            Room room = roomDao.findOne(roomId);
            allRooms = permission.addRoom(room);
            permissionDao.save(permission);
        }
        catch (Exception ex) {
            return "Error updating permission: " + ex.toString();
        }
        return "Room succesfully added for permission id=" + permissionId + "! Permission rooms: " + Arrays.toString(allRooms.toArray());
    }

}

