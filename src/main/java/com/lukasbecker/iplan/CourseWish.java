package com.lukasbecker.iplan;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * CourseWish is a wish from a teacher when and where to hold which course
 * This is a Hibernate Object
 */
@Entity
public class CourseWish implements Serializable {
    private String name;
    private LocalDateTime startDate, endDate;
    private Room room;
    private Teacher teacher;
    private int id;

    public CourseWish(String name, LocalDateTime startDate, LocalDateTime endDate, Room room, Teacher teacher) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.teacher = teacher;
    }

    public CourseWish() {
    }

    @OneToOne
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Column
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @OneToOne
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
}
