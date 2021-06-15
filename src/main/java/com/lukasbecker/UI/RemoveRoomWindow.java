package com.lukasbecker.UI;

import com.lukasbecker.iplan.Room;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveRoomWindow extends JFrame{
    private JComboBox<String> roomComboBox;
    private JButton removeRoomButton;
    private JTextArea textArea1;
    private JPanel removeRoomForm;
    private final EntityManagerFactory emf;
    private List<Room> rooms;

    public RemoveRoomWindow(EntityManagerFactory emf){
        this.emf = emf ;
        rooms = new ArrayList<>() ;
        add(removeRoomForm);
        getRooms();
        setText();
        roomComboBox.addActionListener(e->setText());
        removeRoomButton.addActionListener(e->removeRoom());

    }
    private void removeRoom(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Room room = em.find(Room.class, rooms.get(roomComboBox.getSelectedIndex()).getRoomNr()) ;
            em.remove(room);
            et.commit();
            JOptionPane.showMessageDialog(removeRoomForm,"Room removed successfully!",
                    "Success",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
           int resp =  JOptionPane.showConfirmDialog(removeRoomForm,
                    "Error: Could not delete Room because there are still courses in it! Open 'Delete Course window?'",
                    e.getMessage(),JOptionPane.YES_NO_OPTION);
            et.rollback();
            if(resp == JOptionPane.YES_OPTION){
                RemoveCourseWindow rcw = new RemoveCourseWindow(emf);
                rcw.pack();
                rcw.setVisible(true);
            }
        }
        getRooms();
        setText();

    }
    private void setText(){
        Room room = rooms.get(roomComboBox.getSelectedIndex());
        String content = String.format("Room name: %s%nRoom number: %d",
                room.getRoomName(),room.getRoomNr());
        textArea1.setText(content);
    }


    private void getRooms(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        roomComboBox.removeAllItems();
        String query = "SELECT r FROM Room r WHERE r.id IS NOT NULL";
        TypedQuery<Room> tq = em.createQuery(query, Room.class);
        et.begin();
        try {
            rooms = tq.getResultList();
            rooms.forEach(c -> roomComboBox.addItem(c.getRoomName()));
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }
    }
}
