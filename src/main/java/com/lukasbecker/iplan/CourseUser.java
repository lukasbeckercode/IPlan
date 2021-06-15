package com.lukasbecker.iplan;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CourseUser <br />
 * This Class is needed to store which user is inscribed to which course
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:44
 */

@Entity
public class CourseUser implements Serializable {
    private Course course;
    private User user;
    private int id;

    /**
     * default Constructor
     */
    public CourseUser() {
    }

    /**
     * Constructor
     * @param course a course a selected user is inscribed to
     * @param user te selected user
     */
    public CourseUser(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    /**
     * Course getter
     * @return the course a user is inscribed to
     */
    @ManyToOne
    public Course getCourse() {
        return course;
    }

    /**
     * Course setter
     * @param course the course a user wants to inscribe to
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * User getter
     * @return the inscribed user
     */
    @ManyToOne
    public User getUser() {
        return user;
    }

    /**
     * user setter
     * @param user the user that wants to inscribe
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * id setter
     * not used as the id is auto-generated
     * @param id the id of a CourseUser Entry
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for the course id
     * auto-generated!
     * @return the id of a courseUser Entry
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
}
