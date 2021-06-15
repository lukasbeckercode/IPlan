package com.lukasbecker.iplan;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * can change, add and remove every piece of information on the System
 */
@Entity
public class Admin extends User implements Serializable {

    /**
     * Constructor
     * @param userName chosen username
     * @param password chosen password
     */
    public Admin(String userName, String password) {
        super(userName, password);
    }

    /**
     * default Constructor
     */
    public Admin() {

    }
}
