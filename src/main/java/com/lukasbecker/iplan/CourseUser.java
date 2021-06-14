package com.lukasbecker.iplan;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CourseUser implements Serializable {
    private Course course;
    private User user;
    private int id;

    public CourseUser() {
    }

    public CourseUser(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    @OneToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
}
