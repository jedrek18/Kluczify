package pl.kluczify.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kluczify.server.models.User;
import pl.kluczify.server.models.UserDao;

/**
 * Created by jedrek on 17.12.17.
 */
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    //Public methods

    @RequestMapping("/create-user")
    @ResponseBody
    public String create(String userName, String firstName, String lastName, String emailAddress, String password) {
        User user = null;
        try {
            user = new User(userName, firstName, lastName, emailAddress, password);

                userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    @RequestMapping("/delete-user")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    @RequestMapping("/get-user-by-email")
    @ResponseBody
    public String getByEmail(String emailAddress) {
        String userId;
        try {
            User user = userDao.findByEmailAddress(emailAddress);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping("/update-user")
    @ResponseBody
    public String updateUser(long id, String firstName, String lastName, String emailAddress) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(emailAddress);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    @RequestMapping("/update-user-password")
    @ResponseBody
    public String updateUserPassword(long id, String userName) {
        try {
            User user = userDao.findOne(id);
            user.setUserName(userName);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the password for user: " + ex.toString();
        }
        return "User password succesfully updated!";
    }

    @RequestMapping("/update-user-name")
    @ResponseBody
    public String updateUserName(long id, String userName) {
        try {
            User user = userDao.findOne(id);
            user.setUserName(userName);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the username: " + ex.toString();
        }
        return "Username succesfully updated!";
    }
}
