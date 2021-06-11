package com.lukasbecker.iplan;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * a student can join courses, but no two courses may be at the same time. Also a student gets a timetable
 * @see User
 */
@Entity
public class Student extends User implements Serializable {
    private List<Course> courses = new ArrayList<>();
    public Student(String userName, String password) {
        super(userName, password);
    }

    public Student() {

    }

    public void addCourse(Course course){
        courses.add(course);
    }

    @OneToMany
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses){
        this.courses = courses;
    }
}
