package com.lukasbecker.iplan;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Teacher <br />
 * A teacher teaches a course and can send preferred times of teaching
 * @see User
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:48
 */

@Entity
public class Teacher extends User implements Serializable {
    /**
     * Constructor
     * @param userName username of a teacher
     * @param password password of a teacher
     */
    public Teacher(String userName, String password) {
        super(userName, password);
    }

    /**
     * default constructor
     */
    public Teacher() {

    }
}
