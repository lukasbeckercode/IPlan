package com.lukasbecker.iplan;

import java.util.ArrayList;
import java.util.List;

/**
 * a student can join courses, but no two courses may be at the same time. Also a student gets a timetable
 * @see User
 */
public class Student extends User{
    private List<Course> courses = new ArrayList<>();
    public Student(String userName, String password) {
        super(userName, password);
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
