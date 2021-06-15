package com.lukasbecker.UI;

import com.lukasbecker.iplan.CourseWish;

import javax.swing.*;
import java.util.List;


/**
 * CourseWishViewer <br />
 * shows the assistent the status of his course wishes
 * @author Lukas Becker
 * Last Change: 15/06/2021, 13:36
 */

public class CourseWishViewer extends JFrame {
    private JTextArea textArea1;
    private JPanel wishViewerFrame;

    public CourseWishViewer(List<CourseWish> courseWishes){
        add(wishViewerFrame);
        textArea1.setText("");

        courseWishes.forEach(cw ->{
            String line = String.format("Course Title: %s Status: %s%n",cw.getName(),cw.getStatus());
            textArea1.append(line);
        });
    }
}
