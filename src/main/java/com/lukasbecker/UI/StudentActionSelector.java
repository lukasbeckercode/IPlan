package com.lukasbecker.UI;

import com.lukasbecker.iplan.Checker;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * StudentActionSelector <br />
 * lets a Student decide what to do
 * @author Lukas Becker
 * Last Change: 15/06/2021, 22:46
 */

public class StudentActionSelector extends JFrame {
    private JPanel studentSelectorFrame;
    private JButton inscribeToCourseBtn;
    private JButton showTimeTableBtn;
    private JButton removeCourseBtn;

    /**
     * Constructor
     *
     * @param checker used later..
     * @param emf     used for hibernate
     */
    public StudentActionSelector(Checker checker, EntityManagerFactory emf) {
        add(studentSelectorFrame);
        inscribeToCourseBtn.addActionListener(e -> {
            InscribeToCourse inscribeToCourse = new InscribeToCourse(checker, emf);
            inscribeToCourse.pack();
            inscribeToCourse.setVisible(true);
        });

        showTimeTableBtn.addActionListener(e->{
            Desktop browser = Desktop.getDesktop();
            try {
                browser.browse(new URI("http://localhost:8080"));
            } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });

        removeCourseBtn.addActionListener(e->{
            RemoveCourseUserWindow rcuw = new RemoveCourseUserWindow(emf);
            rcuw.pack();
            rcuw.setVisible(true);
        });
    }
}
