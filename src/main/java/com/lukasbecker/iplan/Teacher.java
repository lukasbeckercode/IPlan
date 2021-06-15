package com.lukasbecker.iplan;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * A teacher teaches a course and can send preferred times of teaching
 * @see User
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
