package com.lukasbecker.iplan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
    Room r;
    Teacher t;
    Course c1,c2,c3;
    Checker checker = new Checker();
    @BeforeEach
    void setUp(){
        r = new Room(1,"test");
        t = new Teacher("teacher1","1234");
        c1 = new Course();
        c2 = new Course();
        c3 = new Course();

        c1.setRoom(r);
        c1.setTeacher(t);
        c1.setCourseName("Test1");
        c1.setCourseID(1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy,HH:mm");
        Date d1 = new Date();
        Date d2 = new Date();
        try {
            d1 = sdf.parse("12.06.2021,13:00");
            d2 = sdf.parse("12.06.2021,14:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c1.setStartDate(d1);
        c1.setEndDate(d2);


        c2.setRoom(r);
        c2.setTeacher(t);
        c2.setCourseName("Test2");
        c2.setCourseID(2);
        Date d3 = new Date();
        Date d4 = new Date();
        try {
            d3 = sdf.parse("12.06.2021,15:00");
            d4 = sdf.parse("12.06.2021,16:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c2.setStartDate(d3);
        c2.setEndDate(d4);


        c3.setRoom(r);
        c3.setTeacher(t);
        c3.setCourseName("Test3");
        c3.setCourseID(3);
        Date d5 = new Date();
        Date d6 = new Date();
        try {
            d5 = sdf.parse("12.06.2021,15:00");
            d6 = sdf.parse("12.06.2021,17:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c3.setStartDate(d5);
        c3.setEndDate(d6);

        checker.addCourse(c1);
        checker.addCourse(c2);
    }

    @org.junit.jupiter.api.Test
    void checkRoom() {
       ERRORS e =  checker.checkRoom();
       assertEquals(ERRORS.OK,e);

       checker.addCourse(c3);
       ERRORS e2 = checker.checkRoom();
       assertEquals(ERRORS.OVERLAPPING_ROOM,e2);
    }

    @org.junit.jupiter.api.Test
    void checkTeacher() {
        ERRORS e =  checker.checkTeacher();
        assertEquals(ERRORS.OK,e);
        checker.addCourse(c3);
        ERRORS e2 = checker.checkTeacher();
        assertEquals(ERRORS.OVERLAPPING_TEACHER,e2);
    }

    @org.junit.jupiter.api.Test
    void checkCourseTime() {
        ERRORS e =  checker.checkCourseTime();
        assertEquals(ERRORS.OK,e);
        checker.addCourse(c3);
        ERRORS e2 = checker.checkCourseTime();
        assertEquals(ERRORS.OVERLAPPING_COURSE,e2);
    }

    @AfterEach
    void teardown(){
        checker.removeCourse(2);
    }
}