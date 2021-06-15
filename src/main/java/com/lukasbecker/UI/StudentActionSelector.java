package com.lukasbecker.UI;

import com.lukasbecker.iplan.Checker;

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
     */
    public StudentActionSelector(Checker checker, EntityManagerFactory emf) {
        add(studentSelectorFrame);
        inscribeToCourseBtn.addActionListener(e -> {
            InscribeToCourse inscribeToCourse = new InscribeToCourse(checker, emf);
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
