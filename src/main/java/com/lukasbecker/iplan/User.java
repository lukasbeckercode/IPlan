package com.lukasbecker.iplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User <br />
 * Every user on this system needs a username and a password
 * This is a very unsafe way of storing passwords!!
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:48
 */

@Entity
public class User implements Serializable {
    private String userName, password;
    private static User currentUser;

    /**
     * Constructor
     * @param userName the chosen username
     * @param password a terribly unsafe password (PLAIN TEXT STORAGE)
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * default constructor
     */
    public User() {

    }

    /**
     * getter for username
     * @return username
     */
    @Id
    @Column
    public String getUserName(){
        return userName;
    }

    /**
     * setter for username
     * @param userName the preferred username
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * getter for password
     * @return the password of a user
     */
    @Column
    public String getPassword() {
        return password;
    }

    /**
     * setter for password
     * @param password the password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter for the user that is currently logged in
     * @return the currently logged in user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Setter for the currently logged in user
     * @param currentUser the currently logged in user
     */
    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }
}
