package com.lukasbecker.thymeleaf;

import com.lukasbecker.iplan.CourseUser;

import java.util.Comparator;

public class CourseUserTimeComparator implements Comparator<CourseUser> {
    @Override
    public int compare(CourseUser o1, CourseUser o2) {
        return o1.getCourse().getStartDate().compareTo(o2.getCourse().getStartDate());
    }
}
