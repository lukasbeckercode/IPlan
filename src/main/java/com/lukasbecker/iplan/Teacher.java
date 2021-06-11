package com.lukasbecker.iplan;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * A teacher teaches a course and can send preferred times of teaching
 * @see User
 */
@Entity
public class Teacher extends User implements Serializable {
    public Teacher(String userName, String password) {
        super(userName, password);
    }

    public Teacher() {

    }
}
