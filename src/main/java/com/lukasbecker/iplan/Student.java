package com.lukasbecker.iplan;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Student <br />
 * a student can join courses, but no two courses may be at the same time. Also a student gets a timetable
 * @see User
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:48
 */

@Entity
public class Student extends User implements Serializable {

    /**
     * Constructor
     * @param userName username of a student
     * @param password password of a student
     */
    public Student(String userName, String password) {
        super(userName, password);
    }

    /**
     * default constructor
     */
    public Student() {

    }
}
