package com.lukasbecker.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * app <br />
 * runs the thymeleaf server
 * @author Lukas Becker
 * Last Change: 15/06/2021, 11:02
 */
@SpringBootApplication
public class app {

    /**
     * runs the thymeleaf server to display the timetable
     * @param args needs to be passed
     */
    public static void run(String[] args){
        SpringApplication.run(app.class,args);
    }
}
