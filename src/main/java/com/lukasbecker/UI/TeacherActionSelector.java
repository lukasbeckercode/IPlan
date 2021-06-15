package com.lukasbecker.UI;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TeacherActionSelector extends JFrame {
    private JPanel teacherSelectorFrame;
    private JButton wishForCourseDateButton;
    private JButton viewMyCourseTimetableButton;

    public TeacherActionSelector(EntityManagerFactory emf) {
        add(teacherSelectorFrame);
        wishForCourseDateButton.addActionListener(e->{
            CourseWisher cw = new CourseWisher(emf);
            cw.pack();
            cw.setVisible(true);
        });
        viewMyCourseTimetableButton.addActionListener(e->{
            Desktop browser = Desktop.getDesktop();
            try {
                browser.browse(new URI("http://localhost:8080"));
            } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
