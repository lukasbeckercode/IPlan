package com.lukasbecker.UI;

import com.lukasbecker.iplan.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * InscribeToCourse <br />
 * Lets a Student inscribe to a course
 * @author Lukas Becker
 * Last Change: 15/06/2021, 22:57
 */

public class InscribeToCourse extends JFrame {
    private final EntityManagerFactory emf;
    private List<Course> courses;
    private final List<Course> studentCourses;
    private JPanel inscribePanel;
    private JComboBox<String> coursesComboBox;
    private JButton inscribeBtn;
    private final Checker checker;

    /**
     * Constructor
     *
     * @param checker to check course date and time
     * @param emf     used for hibernate
     */
    public InscribeToCourse(Checker checker, EntityManagerFactory emf) {
        this.emf = emf;
        this.checker = checker;
        studentCourses = new ArrayList<>();
        getCourses();
        getStudentCourses();
        add(inscribePanel);

        inscribeBtn.addActionListener(e -> inscribe());
    }

    /**
     * adds the course to the students course list
     */
    private void inscribe() {
        ERRORS err = checker.checkCourseTime();
        if(err != ERRORS.OK){
            JOptionPane.showMessageDialog(inscribePanel,"Overlapping Courses: "+err,
                    "Overlapping course",JOptionPane.ERROR_MESSAGE);
        }
       CourseUser courseUser =  new CourseUser(courses.get(coursesComboBox.getSelectedIndex()), User.getCurrentUser());

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {

            et.begin();
            em.persist(courseUser);
            et.commit();
            JOptionPane.showMessageDialog(inscribePanel,"Inscribed successfully!",
                    "Success",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            et.rollback();
            JOptionPane.showMessageDialog(inscribePanel,"An Error occurred, please inform an admin!",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * gets all the existing courses from the database
     */
    private void getCourses() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        coursesComboBox.removeAllItems();
        String query = "SELECT c FROM Course c WHERE c.id IS NOT NULL";
        TypedQuery<Course> tq = em.createQuery(query, Course.class);
        et.begin();
        try {
            courses = tq.getResultList();
            courses.forEach(c -> coursesComboBox.addItem(c.getCourseName()));
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * gets the courses the student is inscribed to
     */
    private void getStudentCourses() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String query = "SELECT c FROM CourseUser c WHERE c.id IS NOT NULL";
        TypedQuery<CourseUser> tq = em.createQuery(query, CourseUser.class);
        et.begin();
        try {
            tq.getResultList().forEach(r->{
                if(r.getUser().getUserName().equals(User.getCurrentUser().getUserName())){
                    studentCourses.add(r.getCourse());
                }
            });
            courses.forEach(c -> coursesComboBox.addItem(c.getCourseName()));
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }
        studentCourses.forEach(checker::addCourse);

    }
}
