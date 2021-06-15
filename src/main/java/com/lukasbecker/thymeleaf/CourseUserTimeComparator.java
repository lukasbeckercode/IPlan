package com.lukasbecker.thymeleaf;

import com.lukasbecker.iplan.CourseUser;

import java.util.Comparator;

/**
 * CourseUserTimeComparator <br />
 * Compares the start time of the courses from a CourseUser class
 * @author Lukas Becker
 * Last Change: 15/06/2021, 22:19
 */

public class CourseUserTimeComparator implements Comparator<CourseUser> {
    @Override
    public int compare(CourseUser o1, CourseUser o2) {
        return o1.getCourse().getStartDate().compareTo(o2.getCourse().getStartDate());
    }
}
