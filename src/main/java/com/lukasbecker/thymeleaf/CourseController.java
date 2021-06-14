package com.lukasbecker.thymeleaf;

import com.lukasbecker.iplan.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CoursesRepo coursesRepo;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String getEmployeeList(Model model) {

        List<Course> courseList = coursesRepo.getCourses();
        model.addAttribute("course",courseList);
        return "courses";
    }
}
