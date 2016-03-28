package com.finalproject.mauritorrez.schoolcontrol.DTO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

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


}