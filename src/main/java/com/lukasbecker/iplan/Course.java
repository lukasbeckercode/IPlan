package com.lukasbecker.iplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a course has a Start and End time, a teacher and a room. Also a Name
 */
public class Course {
    private final Date startDate, endDate;
    private final String courseName;
    private Teacher teacher;
    private Room room;

    /**
     * Constructor
     * @param startDate start date of course
     * @param endDate end date of course
     * @param courseName title of course
     */
    public Course(Date startDate, Date endDate, String courseName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseName = courseName;
        try {
            if(checkTime()!=ERRORS.OK){
                //TODO: give the user an error message
                System.err.println("Invalid Time!");
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

    /**
     * Constructor
     * @param startDate start date of course
     * @param endDate end date of course
     * @param courseName title of course
     * @param teacher teacher teaching a course
     * @param room room in which the course takes place
     */
    public Course(Date startDate, Date endDate, String courseName,Teacher teacher,Room room) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseName = courseName;
        this.teacher = teacher;
        this.room = room;

        try {
            if(checkTime()!=ERRORS.OK){
                //TODO: give the user an error message
                System.err.println("Invalid Time!");
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * checks if the time is within set bounds
     * @return OK if correct, EARLY if too early, LATE if too late
     * @see ERRORS
     * @throws ParseException if parsing from date to time fails, should not occur here!
     */
    private ERRORS checkTime() throws ParseException {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date startTime = timeFormat.parse(timeFormat.format(startDate));
        Date endTime = timeFormat.parse(timeFormat.format(endDate));
        if(startTime.before(timeFormat.parse("08:00"))){
            return ERRORS.EARLY;
        }else if(endTime.after(timeFormat.parse("23:00"))){
            return ERRORS.LATE;
        }

        return ERRORS.OK;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacher() {
        return teacher.getUserName();
    }
}
