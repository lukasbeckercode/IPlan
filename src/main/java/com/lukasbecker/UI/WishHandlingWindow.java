package com.lukasbecker.UI;

import com.lukasbecker.iplan.CourseWish;
import com.lukasbecker.iplan.WISH_STATUS;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

public class WishHandlingWindow extends JFrame {
    private JComboBox<String> wishesComboBox;
    private JButton acceptBtn;
    private JTextArea textArea1;
    private JButton declineButton;
    private JPanel wishHandlingForm;
    private final EntityManagerFactory emf;
    EntityManager em;
    private List<CourseWish> courseWishes ;

    public WishHandlingWindow(EntityManagerFactory emf){
        this.emf = emf;
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
        et.begin();
        em.merge(wish);
        et.commit();
    }
    private void getCourseWishes(){

         em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String query = "SELECT c FROM CourseWish c WHERE c.id IS NOT NULL";
        TypedQuery<CourseWish> tq = em.createQuery(query, CourseWish.class);
        et.begin();
        try {
            courseWishes = tq.getResultList();
            courseWishes.forEach(c -> wishesComboBox.addItem(c.getName()+": "+c.getStatus()));
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }

    }

}
