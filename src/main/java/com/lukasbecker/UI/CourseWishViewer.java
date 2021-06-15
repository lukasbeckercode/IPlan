package com.lukasbecker.UI;

import com.lukasbecker.iplan.CourseWish;

import javax.swing.*;
import java.util.List;

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
