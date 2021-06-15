package com.lukasbecker.thymeleaf;

import com.lukasbecker.iplan.CourseUser;
import com.lukasbecker.iplan.User;
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

    /**
     * maps the data stored in CourseUser to a html page
     * @param model required param
     */
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String getEmployeeList(Model model) {

        List<CourseUser> courseList = coursesRepo.getCourses(User.getCurrentUser());
        model.addAttribute("courses",courseList);
        return "courses";
    }
}
