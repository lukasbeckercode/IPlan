package com.lukasbecker.UI;

import com.lukasbecker.iplan.Room;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.swing.*;

/**
 * AddRoomWindow <br />
 * adds a room to the database
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:54
 */

public class AddRoomWindow extends JFrame {
    private JPanel addRoomPanel;
    private JTextField roomNameTextBox;
    private JTextField roomNumberTextBox;
    private JButton createRoomBtn;
    private final EntityManagerFactory emf;

    /**
     * Constructor
     *
     * @param emf used for hibernate
     */
    public AddRoomWindow(EntityManagerFactory emf) {
        this.emf = emf;
        add(addRoomPanel);
        createRoomBtn.addActionListener(e -> addRoom());
    }

    /**
     * adds the room to the database
     */
    private void addRoom() {
        String name = roomNameTextBox.getText();
        int nr;
        try {
            nr = Integer.parseInt(roomNumberTextBox.getText());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            JOptionPane.showMessageDialog(addRoomPanel, "Error: Room number must ba a number!",
                    "Room Number Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        EntityManager em = emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        et.begin();
        Room r = new Room(nr, name);
        em.persist(r);
        et.commit();
    }
}
