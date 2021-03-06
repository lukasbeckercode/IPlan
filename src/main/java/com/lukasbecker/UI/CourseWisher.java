package com.lukasbecker.UI;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.lukasbecker.iplan.*;

import javax.persistence.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * CourseWisher <br />
 * sends a course wish to the admin
 * @author Lukas Becker
 * Last Change: 15/06/2021, 22:57
 */

public class CourseWisher extends JFrame {
    private JTextField nameTextBox;
    private JComboBox<String> roomComboBox;
    private JButton sendWishButton;
    private JPanel courseWishFrame;
    private DateTimePicker endTimePicker;
    private DateTimePicker startTimePicker;

    private final List<Room> rooms;
    private final EntityManagerFactory emf;

    /**
     * Constructor
     * @param emf used for hibernate
     */
    public CourseWisher(EntityManagerFactory emf){
        this.emf = emf;
        add(courseWishFrame);
        rooms = getRooms();
        sendWishButton.addActionListener(e->sendCourseWish());
    }

    /**
     * sends a course wish to the database for an admin to check
     */
    private void sendCourseWish(){
        String name = nameTextBox.getText();
        LocalDateTime startTime = startTimePicker.getDateTimePermissive();
        LocalDateTime endTime = endTimePicker.getDateTimePermissive();
        Room room = rooms.get(roomComboBox.getSelectedIndex());

        CourseWish cw = new CourseWish(name,startTime,endTime,room,(Teacher) User.getCurrentUser(), WISH_STATUS.PENDING);

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {

            et.begin();
            em.persist(cw);
            et.commit();
            JOptionPane.showMessageDialog(courseWishFrame,"Wish sent successfully!",
                    "Success",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(courseWishFrame,"An Error occurred, please contact an admin!",
                    e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * updates which rooms exist in the database
     *
     * @return a List containing every room that exists in the Database
     */
    List<Room> getRooms() {
        EntityManager EM = emf.createEntityManager();
        String query = "SELECT r FROM Room r WHERE r.id IS NOT NULL";
        roomComboBox.removeAllItems();
        TypedQuery<Room> tq = EM.createQuery(query, Room.class);
        List<Room> rooms = null;
        try {
            rooms = tq.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            EM.close();
        }
        if (rooms != null) {
            rooms.forEach(r -> roomComboBox.addItem(r.getRoomName()));
        }
        return rooms;
    }
}
