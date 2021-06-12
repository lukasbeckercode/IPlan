import com.lukasbecker.iplan.Checker;
import com.lukasbecker.iplan.Course;
import com.lukasbecker.iplan.Student;
import com.lukasbecker.iplan.User;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

/**
 * Lets a Student inscribe to a course
 */
public class InscribeToCourse extends JFrame {
    private final EntityManagerFactory emf;
    private final Student user;
    private List<Course> courses;
    private JPanel inscribePanel;
    private JComboBox<String> coursesComboBox;
    private JButton inscribeBtn;

    /**
     * Constructor
     *
     * @param checker to check course date and time
     * @param emf     used for hibernate
     * @param user    the current user
     */
    public InscribeToCourse(Checker checker, EntityManagerFactory emf, User user) {
        this.emf = emf;
        this.user = (Student) user;
        getCourses();

        add(inscribePanel);

        inscribeBtn.addActionListener(e -> inscribe());
    }

    /**
     * adds the course to the students course list
     * TODO: fix me!
     */
    private void inscribe() {
        user.addCourse(courses.get(coursesComboBox.getSelectedIndex()));
    }

    /**
     * gets all the existing courses from the database
     */
    private void getCourses() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

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
}
