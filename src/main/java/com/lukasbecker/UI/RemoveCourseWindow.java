package com.lukasbecker.UI;

import com.lukasbecker.iplan.Course;
import com.lukasbecker.iplan.Room;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveCourseWindow extends JFrame {
    private JComboBox<String> courseComboBox;
    private JButton removeCourseButton;
    private JTextArea textArea1;
    private JPanel removeCourseForm;
    private final EntityManagerFactory emf;
    private List<Course> courses;

    public RemoveCourseWindow(EntityManagerFactory emf){
        this.emf = emf;
        courses = new ArrayList<>();
        add(removeCourseForm);
        getCourses();
        setText();
        courseComboBox.addActionListener(e->setText());
        removeCourseButton.addActionListener(e->removeCourse());

    }
    private void getCourses(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String query = "SELECT c FROM Course c WHERE c.id IS NOT NULL";
        TypedQuery<Course> tq = em.createQuery(query, Course.class);
        et.begin();
        try {
            courses = tq.getResultList();
            courses.forEach(c -> courseComboBox.addItem(c.getCourseName()));
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }
    }

    private void setText(){
        Course course = courses.get(courseComboBox.getSelectedIndex());
        String content = String.format("Course name: %s%nStart: %s%nEnd: %s%nTeacher: %s%nRoom NR: %s%n",
                course.getCourseName(),course.getStartDate(),course.getEndDate(),course.getTeacher().getUserName(),
                course.getRoom().getRoomName());
        textArea1.setText(content);
    }
    private void removeCourse(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Course course = em.find(Course.class,courses.get(courseComboBox.getSelectedIndex()).getCourseID()) ;
            em.remove(course);
            et.commit();
        }catch (Exception e){
            JOptionPane.showMessageDialog(removeCourseForm,
                    "Error: Could not delete Course because there are still students inscribed to it!",
                    e.getMessage(),JOptionPane.ERROR_MESSAGE);
            et.rollback();
        }

        getCourses();
        setText();

    }


}
