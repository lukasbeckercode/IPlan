package com.lukasbecker.iplan;

import java.util.Comparator;

/**
 * TimeComparator <br />
 * Compares start time of courses
 * @author Lukas Becker
 * Last Change: 15/06/2021, 14:46
 */

public class TimeComparator implements Comparator<Course> {
    /**
     * Compare method
     * @param o1 element 1
     * @param o2 element 2
     * @return the earlier start time of a course
     */
    @Override
    public int compare(Course o1, Course o2) {
        return o1.getStartDate().compareTo(o2.getStartDate());
    }
}
