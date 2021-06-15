package com.lukasbecker.UI;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.lukasbecker.iplan.CourseWish;
import com.lukasbecker.iplan.Room;
import com.lukasbecker.iplan.Teacher;
import com.lukasbecker.iplan.User;

import javax.persistence.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class CourseWisher extends JFrame {
    private JTextField nameTextBox;
    private JComboBox<String> roomComboBox;
    private JButton sendWishButton;
    private JPanel courseWishFrame;
    private DateTimePicker endTimePicker;
    private DateTimePicker startTimePicker;
    EntityManagerFactory emf;

    public CourseWisher(EntityManagerFactory emf){
        this.emf = emf;
        add(courseWishFrame);
        sendWishButton.addActionListener(e->sendCourseWish());
    }


    private void sendCourseWish(){
        String name = nameTextBox.getText();
        LocalDateTime startTime = startTimePicker.getDateTimePermissive();
        LocalDateTime endTime = endTimePicker.getDateTimePermissive();
        List<Room> rooms = getRooms();
        Room room = rooms.get(roomComboBox.getSelectedIndex());

        CourseWish cw = new CourseWish(name,startTime,endTime,room,(Teacher) User.getCurrentUser());

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(cw);
        et.commit();
    }
    /**
     * updates which rooms exist in the database
     *
     * @return a List containing every room that exists in the Database
     */
    List<Room> getRooms() {
        EntityManager EM = emf.createEntityManager();
        String query = "SELECT r FROM Room r WHERE r.id IS NOT NULL";
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
