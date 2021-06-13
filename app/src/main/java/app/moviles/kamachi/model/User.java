package app.moviles.kamachi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private String userId;
    private String userName;
    private String email;
    private String telephone;
    private String descripion;
    private String password;
    private String profilePic;
    private UserType userType;

    public User() {
    }

    public User(String id, String userName, String email, String telephone, String password, UserType userType) {
        this.userId = id;
        this.userName = userName;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.userType = userType;
    }

    public String getDescripion() {
        return descripion;
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

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
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

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
