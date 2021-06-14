package com.lukasbecker.iplan;

import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a course has a Start and End time, a teacher and a room. Also a Name
 */
@Entity
public class Course implements Serializable {
    private Date startDate, endDate;



    private int courseID;
    private String courseName;
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
                JOptionPane.showMessageDialog(null,"Invalid Time: No course before 8am or 11 pm",
                        "Invalid Time",JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null,"Invalid Time: No course before 8am or 11 pm",
                        "Invalid Time",JOptionPane.ERROR_MESSAGE);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public Course() {

    }

    /**
     * checks if the time is within set bounds
     * @return OK if correct, EARLY if too early, LATE if too late
     * @see ERRORS
     * @throws ParseException if parsing from date to time fails, should not occur here!
     */
    public ERRORS checkTime() throws ParseException {
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

    @ManyToOne
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    @Column
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }


    @Column
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return the name of the course
     */
    @Column
    public String getCourseName() {
        return courseName;
    }

    /**
     *
     * @param courseName the name for the course
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     *
     * @return the teacher teaching a course
     */
    @ManyToOne
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     *
     * @param teacher teaching the course
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     *
     * @return course id
     */
    @Id
    public int getCourseID() {
        return courseID;
    }

    /**
     *
     * @param courseID if for the course
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
