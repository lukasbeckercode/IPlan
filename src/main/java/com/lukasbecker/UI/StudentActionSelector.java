package com.lukasbecker.UI;

import com.lukasbecker.iplan.Checker;
import com.lukasbecker.iplan.User;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * lets a Student decide what to do
 */
public class StudentActionSelector extends JFrame {
    private JPanel studentSelectorFrame;
    private JButton inscribeToCourseBtn;
    private JButton showTimeTableBtn;

    /**
     * Constructor
     *
     * @param checker used later..
     * @param emf     used for hibernate
     * @param u       the current user
     */
    public StudentActionSelector(Checker checker, EntityManagerFactory emf, User u) {
        add(studentSelectorFrame);
        inscribeToCourseBtn.addActionListener(e -> {
            InscribeToCourse inscribeToCourse = new InscribeToCourse(checker, emf, u);
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
    }
}
