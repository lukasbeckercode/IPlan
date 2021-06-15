package com.lukasbecker.UI;

import com.lukasbecker.iplan.User;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;

public class TeacherActionSelector extends JFrame {
    private JPanel teacherSelectorFrame;
    private JButton wishForCourseDateButton;
    private JButton viewMyCourseTimetableButton;

    //TODO: implement me!
    public TeacherActionSelector(EntityManagerFactory emf) {
        add(teacherSelectorFrame);
        wishForCourseDateButton.addActionListener(e->{
            CourseWisher cw = new CourseWisher(emf);
            cw.pack();
            cw.setVisible(true);
        });
    }
}
