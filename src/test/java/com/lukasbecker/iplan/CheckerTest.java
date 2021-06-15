package com.lukasbecker.iplan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckerTest {
    private Course course1,course2,course3 ;
    private Checker checker;
    private ERRORS e1,e2,e3;

    @BeforeEach
    void startUp(){
        checker = new Checker();
        LocalDateTime time1 = LocalDateTime.of(2021, 6, 21, 12, 0);
        LocalDateTime time2 = LocalDateTime.of(2021, 6, 21, 13, 0);
        LocalDateTime time3 = LocalDateTime.of(2021, 6, 21, 14, 0);
        LocalDateTime time4 = LocalDateTime.of(2021, 6, 21, 15, 0);
        LocalDateTime time5 = LocalDateTime.of(2021, 6, 21, 14, 30);
        LocalDateTime time6 = LocalDateTime.of(2021, 6, 21, 15, 0);
        Teacher teacher = new Teacher("user", "pass");
        Room room = new Room(1, "room");
        course1 = new Course(time1, time2,"Test1", teacher, room);
        course2 = new Course(time3, time4,"Test2", teacher, room);
        course3 = new Course(time5, time6,"Test3", teacher, room);
    }
    @Test
    void checkRoom() {

        checker.addCourse(course1);
        e1 = checker.checkRoom();
        checker.addCourse(course2);
        e2 = checker.checkRoom();
        checker.addCourse(course3);
        e3 = checker.checkRoom();

        assertEquals(ERRORS.OK,e1);
        assertEquals(ERRORS.OK,e2);
        assertEquals(ERRORS.OVERLAPPING_ROOM,e3);
    }

    @Test
    void checkTeacher() {
        checker.addCourse(course1);
        e1 = checker.checkTeacher();
        checker.addCourse(course2);
        e2 = checker.checkTeacher();
        checker.addCourse(course3);
        e3=checker.checkTeacher();

        assertEquals(ERRORS.OK,e1);
        assertEquals(ERRORS.OK,e2);
        assertEquals(ERRORS.OVERLAPPING_TEACHER,e3);
    }

    @Test
    void checkCourseTime() {
        checker.addCourse(course1);
        e1 = checker.checkCourseTime();
        checker.addCourse(course2);
        e2 = checker.checkCourseTime();
        checker.addCourse(course3);
        e3 = checker.checkCourseTime();


        assertEquals(ERRORS.OK,e1);
        assertEquals(ERRORS.OK,e2);
        assertEquals(ERRORS.OVERLAPPING_COURSE,e3);
    }

    @AfterEach
    void teardown(){
        checker.courseList.clear();
    }
}