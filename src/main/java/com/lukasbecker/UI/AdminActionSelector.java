package com.lukasbecker.UI;

import com.lukasbecker.iplan.Checker;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;

/**
 * AdminActionSelector <br />
 * Lets an Admin choose what he wants to do
 * @author Lukas Becker
 * Last Change: 15/06/2021, 16:34
 */

public class AdminActionSelector extends JFrame {
    private JPanel adminSelectorFrame;
    private JButton addRoomBtn;
    private JButton addACourseButton;
    private JButton viewCourseWishesBtn;
    private JButton removeRoom;
    private JButton removeCourseButton;

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
        removeRoom.addActionListener(e->{
            RemoveRoomWindow rrw = new RemoveRoomWindow(emf);
            rrw.pack();
            rrw.setVisible(true);
        });
        removeCourseButton.addActionListener(e->{
            RemoveCourseWindow rcw = new RemoveCourseWindow(emf);
            rcw.pack();
            rcw.setVisible(true);
        });
    }
}
