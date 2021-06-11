package com.lukasbecker.iplan;

/**
 * Every user on this system needs a username and a password
 * This is a very unsafe way of storing passwords!!
 */
public class User {
    private final String userName, password;

    /**
     * Constructor
     * @param userName the chosen username
     * @param password a terribly unsafe password (PLAIN TEXT STORAGE)
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public String getUserName(){
        return userName;
    }
}
