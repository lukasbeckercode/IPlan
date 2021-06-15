package com.lukasbecker.UI;

import com.lukasbecker.iplan.Checker;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;

/**
 * Lets an Admin choose what he wants to do
 */
public class AdminActionSelector extends JFrame {
    private JPanel adminSelectorFrame;
    private JButton addRoomBtn;
    private JButton addACourseButton;
    private JButton viewCourseWishesBtn;

    /**
     * Constructor
     *
     * @param checker used to check courses,dates, teachers and rooms
     * @param emf     used for hibernate
     */
    public AdminActionSelector(Checker checker, EntityManagerFactory emf) {
        add(adminSelectorFrame);
        addRoomBtn.addActionListener(e -> {
            AddRoomWindow addRoomWindow = new AddRoomWindow(emf);
            addRoomWindow.pack();
            addRoomWindow.setVisible(true);
        });

        addACourseButton.addActionListener(e -> {
            AddCourseWindow addCourseWindow = new AddCourseWindow(checker, emf);
            addCourseWindow.pack();
            addCourseWindow.setVisible(true);
        });

        viewCourseWishesBtn.addActionListener(e->{
            WishHandlingWindow whw = new WishHandlingWindow(emf);
            whw.pack();
            whw.setVisible(true);
        });
    }
}
