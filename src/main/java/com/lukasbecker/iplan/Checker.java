package com.lukasbecker.iplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Checker {
    List<Course> courseList = new ArrayList<>();
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date endTime = null;
    Date startTime = null;

    /**
     * adds a course to the List
     * @param course the course to be added
     */
    public void addCourse(Course course){
        courseList.add(course);
    }

    /**
     * checks if a room is available for a course
     * @return OK if it is, OVERLAPPING_ROOM if not
     * @see ERRORS
     */
    public ERRORS checkRoom(){
        if(courseList.size()==0)
            return ERRORS.OK;


        for(int i = 1; i<courseList.size(); i++){

            try {
                startTime = timeFormat.parse(timeFormat.format(courseList.get(i).getStartDate()));
                endTime = timeFormat.parse(timeFormat.format(courseList.get(i-1).getEndDate()));
            }catch (ParseException pe){
                //
                return ERRORS.UNEXPECTED_EXCEPTION;
            }

            if(startTime.before(endTime)&&courseList.get(i-1).getRoom()==courseList.get(i).getRoom()){
                return ERRORS.OVERLAPPING_ROOM;
            }
        }
        return ERRORS.OK;
    }

    /**
     * checks if a teacher is available for a course
     * @return OK if they are, OVERLAPPING_TEACHER if not
     * @see ERRORS
     */
    public ERRORS checkTeacher(){
        if(courseList.size()==0)
            return ERRORS.OK;


        for(int i = 1; i<courseList.size(); i++){
            try {
                startTime = timeFormat.parse(timeFormat.format(courseList.get(i).getStartDate()));
                endTime = timeFormat.parse(timeFormat.format(courseList.get(i-1).getEndDate()));
            }catch (ParseException pe){
                //
                return ERRORS.UNEXPECTED_EXCEPTION;
            }

            if(startTime.before(endTime)&& courseList.get(i - 1).getTeacher().equals(courseList.get(i).getTeacher())){
                return ERRORS.OVERLAPPING_TEACHER;
            }
        }
        return ERRORS.OK;
    }

    /**
     * Checks if two courses overlap
     * FOR STUDENTS
     * @return OK if no two courses overlap, OVERLAPPING_COURSE if they do
     * @see ERRORS
     */
    public ERRORS checkCourseTime(){
        if(courseList.size()==0)
            return ERRORS.OK;


        for(int i = 1; i<courseList.size(); i++){
            try {
                startTime = timeFormat.parse(timeFormat.format(courseList.get(i).getStartDate()));
                endTime = timeFormat.parse(timeFormat.format(courseList.get(i-1).getEndDate()));
            }catch (ParseException pe){
                //
                return ERRORS.UNEXPECTED_EXCEPTION;
            }

            if(startTime.before(endTime)){
                return ERRORS.OVERLAPPING_COURSE;
            }
        }
        return ERRORS.OK;
    }

    public void removeCourse(int id){
        courseList.remove(id);
    }


}
