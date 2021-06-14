package com.lukasbecker.iplan;

import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * a student can join courses, but no two courses may be at the same time. Also a student gets a timetable
 * @see User
 */
@Entity
public class Student extends User implements Serializable {

    public Student(String userName, String password) {
        super(userName, password);
    }

    public Student() {

    }
}
