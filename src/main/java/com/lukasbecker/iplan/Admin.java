

package com.lukasbecker.iplan;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Admin <br />
 * can add and delete courses and rooms
 * can accept and decline course wishes
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:44
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
