package com.lukasbecker.UI;

import com.lukasbecker.iplan.CourseWish;
import com.lukasbecker.iplan.WISH_STATUS;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class WishHandlingWindow extends JFrame {
    private JComboBox<String> wishesComboBox;
    private JButton acceptBtn;
    private JTextArea textArea1;
    private JButton declineButton;
    private JPanel wishHandlingForm;
    private final EntityManagerFactory emf;
    private EntityManager em;
    private final List<CourseWish> courseWishes ;

    public WishHandlingWindow(EntityManagerFactory emf){
        this.emf = emf;
        courseWishes = new ArrayList<>();
        add(wishHandlingForm);
        getCourseWishes();
        updateTextBox(courseWishes.get(wishesComboBox.getSelectedIndex()));
        wishesComboBox.addActionListener(e->updateTextBox(courseWishes.get(wishesComboBox.getSelectedIndex())));
        acceptBtn.addActionListener(e->setCourseStatus(courseWishes.get(wishesComboBox.getSelectedIndex()),WISH_STATUS.ACCEPTED));
        declineButton.addActionListener(e->setCourseStatus(courseWishes.get(wishesComboBox.getSelectedIndex()),WISH_STATUS.DECLINED));

    }

    private void updateTextBox(CourseWish wish){
        String content = String.format("Title: %s%nStart: %s%nEnd: %s%nRoom: %s%nTeacher: %s%n",
                wish.getName(),wish.getStartDate(),wish.getEndDate(),wish.getRoom().getRoomName(),
                wish.getTeacher().getUserName());
        textArea1.setText(content);
    }
    private void setCourseStatus(CourseWish wish,WISH_STATUS status){
        wish.setStatus(status);
        em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {

            et.begin();
            em.merge(wish);
            et.commit();
            JOptionPane.showMessageDialog(wishHandlingForm,"Status updated successfully!",
                    "Success",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(wishHandlingForm,"An Error occurred. Send help please...",
                    e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }
    private void getCourseWishes(){

         em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        wishesComboBox.removeAllItems();
        String query = "SELECT c FROM CourseWish c WHERE c.id IS NOT NULL";
        TypedQuery<CourseWish> tq = em.createQuery(query, CourseWish.class);
        et.begin();
        try {
             tq.getResultList().forEach(cw->{
                 if(cw.getStatus()==WISH_STATUS.PENDING){
                     courseWishes.add(cw);
                 }
             });
            courseWishes.forEach(c -> wishesComboBox.addItem(c.getName()+": "+c.getStatus()));
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }

    }

}
