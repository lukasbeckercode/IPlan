package com.lukasbecker.iplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Every user on this system needs a username and a password
 * This is a very unsafe way of storing passwords!!
 */
@Entity
public class User implements Serializable {
    private String userName, password;

    /**
     * Constructor
     * @param userName the chosen username
     * @param password a terribly unsafe password (PLAIN TEXT STORAGE)
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    @Id
    @Column
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
