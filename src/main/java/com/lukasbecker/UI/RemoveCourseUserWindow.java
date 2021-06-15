package com.lukasbecker.UI;

import com.lukasbecker.iplan.Course;
import com.lukasbecker.iplan.CourseUser;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

public class RemoveCourseUserWindow extends JFrame{
    private JComboBox<String> courseComboBox;
    private JButton removeButton;
    private JTextArea textArea1;
    private JPanel removeWindowForm;
    private List<CourseUser> courseUsers;
    private final EntityManagerFactory emf;

    public RemoveCourseUserWindow(EntityManagerFactory emf){
        add(removeWindowForm);
        this.emf = emf;
        getCourses();
        setText();
        removeButton.addActionListener(e->removeCourse());
    }

    private void getCourses(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        courseComboBox.removeAllItems();
        String query = "SELECT c FROM CourseUser c WHERE c.id IS NOT NULL";
        TypedQuery<CourseUser> tq = em.createQuery(query, CourseUser.class);
        et.begin();
        try {
            courseUsers = tq.getResultList();
            courseUsers.forEach(c -> courseComboBox.addItem(c.getCourse().getCourseName()));
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }
    }

    private void setText(){
        Course course = courseUsers.get(courseComboBox.getSelectedIndex()).getCourse();
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
            CourseUser course = em.find(CourseUser.class, courseUsers.get(courseComboBox.getSelectedIndex()).getId()) ;
            em.remove(course);
            et.commit();

            JOptionPane.showMessageDialog(removeWindowForm,"Course deleted successfully!",
                    "Success!",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(removeWindowForm,
                    "Error: Could not delete Course! Please contact Admin for further help.",
                    e.getMessage(),JOptionPane.ERROR_MESSAGE);
            et.rollback();
        }

        getCourses();
        setText();

    }
}
