package com.finalproject.mauritorrez.schoolcontrol.DTO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mauri on 3/25/2016.
 */
public class UserDTO implements Serializable{

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    private String username;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private String password;

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    private String userType;


    public UUID getUserGuid() {
        return userGuid;
    }
    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }
    private UUID userGuid;

}