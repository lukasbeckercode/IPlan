
package com.lukasbecker.iplan;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * CourseWish <br />
 * CourseWish is a wish from a teacher when and where to hold which course
 * This is a Hibernate Object
 * @author Lukas Becker
 * Last Change: 15/06/2021, 12:27
 */
@Entity
public class CourseWish implements Serializable {
    private String name;
    private LocalDateTime startDate, endDate;
    private Room room;
    private Teacher teacher;
    private WISH_STATUS status;
    private int id;

    /**
     * Constructor
     * @param name name of a course
     * @param startDate preferred start date
     * @param endDate preferred end date
     * @param room preferred room
     * @param teacher teacher to tech this course
     * @param status tells the teacher what the status of the wish is
     */
    public CourseWish(String name, LocalDateTime startDate, LocalDateTime endDate, Room room, Teacher teacher,WISH_STATUS status) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.teacher = teacher;
        this.status = status;
    }

    /**
     * default constructor
     */
    public CourseWish() {
    }

    /**
     * gets the status of a course wish
     * @return course wish status
     */
    public WISH_STATUS getStatus() {
        return status;
    }

    /**
     * sets the course wish status
     * @param status the status to be set
     */
    public void setStatus(WISH_STATUS status) {
        this.status = status;
    }

    /**
     * getter for teacher
     * @return preferred teacher
     */
    @OneToOne
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * setter for teacher
     * @param teacher preferred teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * getter for course name
     * @return preferred course name
     */
    @Column
    public String getName() {
        return name;
    }

    /**
     * setter for course name
     * @param name preferred name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for start date
     * @return chosen start date
     */
    @Column
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * setter for start date
     * @param startDate preferred start date
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * getter for and date
     * @return the chosen end date
     */
    @Column
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * setter for end date
     * @param endDate the preferred and date
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * getter for room
     * @return the chosen room
     */
    @OneToOne
    public Room getRoom() {
        return room;
    }

    /**
     * setter for room
     * @param room preferred room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * setter for CourseWish id
     * not used here as this is an auto-generated value
     * @param id the id of a CourseWish
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for the id
     * @return the id of a CourseWish
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
}
