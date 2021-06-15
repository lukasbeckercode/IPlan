package com.lukasbecker.UI;

import com.lukasbecker.iplan.CourseWish;
import com.lukasbecker.iplan.WISH_STATUS;

import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class TeacherActionSelector extends JFrame {
    private JPanel teacherSelectorFrame;
    private JButton wishForCourseDateButton;
    private JButton viewMyCourseTimetableButton;
    private final EntityManagerFactory emf;

    private List<CourseWish> courseWishes ;

    public TeacherActionSelector(EntityManagerFactory emf) {
        add(teacherSelectorFrame);
        this.emf = emf;

        checkWishStatus();
        wishForCourseDateButton.addActionListener(e->{
            CourseWisher cw = new CourseWisher(emf);
            cw.pack();
            cw.setVisible(true);
        });
        viewMyCourseTimetableButton.addActionListener(e->{
            Desktop browser = Desktop.getDesktop();
            try {
                browser.browse(new URI("http://localhost:8080"));
            } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void checkWishStatus(){
        getCourseWishes();
        courseWishes.forEach(cw->{
            if(cw.getStatus()== WISH_STATUS.DECLINED){
               int resp =  JOptionPane.showConfirmDialog(teacherSelectorFrame,
                        "Warning: At least one of your course wishes was declined. Do you want to view your wishes?",
                       "Declined wish found", JOptionPane.YES_NO_OPTION);
               if(resp == JOptionPane.YES_OPTION){
                   CourseWishViewer cwv = new CourseWishViewer(courseWishes);
                   cwv.pack();
                   cwv.setVisible(true);
               }
            }
        });
    }

    private void getCourseWishes(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String query = "SELECT c FROM CourseWish c WHERE c.id IS NOT NULL";
        TypedQuery<CourseWish> tq = em.createQuery(query, CourseWish.class);
        et.begin();
        try {
            courseWishes = tq.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }

    }
}
