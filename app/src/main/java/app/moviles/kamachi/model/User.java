package app.moviles.kamachi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private String userId;
    private String userName;
    private String email;
    private String thelephone;
    private String password;
    private String profilePic;
    private List<Event> userEvents;
    private UserType userType;

    public User() {
    }

    public User(String userName, String email, String password, UserType userType) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        userEvents = new ArrayList<Event>();
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getThelephone() {
        return thelephone;
    }

    public String getPassword() {
        return password;
    }

    public List<Event> getUserEvents() {
        return userEvents;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setThelephone(String thelephone) {
        this.thelephone = thelephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserEvents(List<Event> userEvents) {
        this.userEvents = userEvents;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void addEvent(Event event){
        userEvents.add(event);
    }
}
