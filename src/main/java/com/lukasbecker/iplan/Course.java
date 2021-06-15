package com.lukasbecker.iplan;

import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Course <br />
 * a course has a Start and End time, a teacher and a room. Also a Name
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:44
 */

@Entity
public class Course implements Serializable {
    private LocalDateTime startDate, endDate;



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
    public Course(LocalDateTime startDate, LocalDateTime endDate, String courseName) {
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
    public Course(LocalDateTime startDate, LocalDateTime endDate, String courseName, Teacher teacher, Room room) {
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

    /**
     * default constructor
     */
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
        Date startTime = timeFormat.parse(startDate.toString().substring(startDate.toString().indexOf("T")+1));
        Date endTime = timeFormat.parse(endDate.toString().substring(endDate.toString().indexOf("T")+1));
        if(startTime.before(timeFormat.parse("08:00"))){
            return ERRORS.EARLY;
        }else if(endTime.after(timeFormat.parse("23:00"))){
            return ERRORS.LATE;
        }

        return ERRORS.OK;
    }

    /**
     * room getter
     * @return the room a course is in
     */
    @ManyToOne
    public Room getRoom() {
        return room;
    }

    /**
     * room setter
     * @param room the room a course is in
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * startDateTime Getter
     * @return the date and time of the course start
     */
    @Column
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Setter for start date and Time
     * @param startDate the preferred start date and time
     */
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }

    /**
     * end date and time getter
     * @return the end date and time of a course
     */
    @Column
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * end date and time setter
     * @param endDate the preferred and date and time of a course
     */
    public void setEndDate(LocalDateTime endDate) {
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
