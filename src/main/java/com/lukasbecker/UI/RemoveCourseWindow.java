package com.lukasbecker.UI;

import com.lukasbecker.iplan.Course;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RemoveCourseWindow <br />
 * Dialoge window to remove a course
 * @author Lukas Becker
 * Last Change: 15/06/2021, 23:03
 */

public class RemoveCourseWindow extends JFrame {
    private JComboBox<String> courseComboBox;
    private JButton removeCourseButton;
    private JTextArea textArea1;
    private JPanel removeCourseForm;
    private final EntityManagerFactory emf;
    private List<Course> courses;

    /**
     * Constructor
     * @param emf used for hibernate
     */
    public RemoveCourseWindow(EntityManagerFactory emf){
        this.emf = emf;
        courses = new ArrayList<>();
        add(removeCourseForm);
        getCourses();
        setText();
        courseComboBox.addActionListener(e->setText());
        removeCourseButton.addActionListener(e->removeCourse());

    }

    /**
     * gets all existing courses
     */
    private void getCourses(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        courseComboBox.removeAllItems();
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

    /**
     * dynamically change the textArea to show course details
     */
    private void setText(){
        Course course = courses.get(courseComboBox.getSelectedIndex());
        String content = String.format("Course name: %s%nStart: %s%nEnd: %s%nTeacher: %s%nRoom NR: %s%n",
                course.getCourseName(),course.getStartDate(),course.getEndDate(),course.getTeacher().getUserName(),
                course.getRoom().getRoomName());
        textArea1.setText(content);
    }

    /**
     * try to remove the Course
     */
    private void removeCourse(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Course course = em.find(Course.class,courses.get(courseComboBox.getSelectedIndex()).getCourseID()) ;
            em.remove(course);
            et.commit();
            JOptionPane.showMessageDialog(removeCourseForm,"Course deleted successfully",
                    "Success",JOptionPane.INFORMATION_MESSAGE);
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
