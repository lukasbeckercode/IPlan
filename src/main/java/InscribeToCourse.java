import com.lukasbecker.iplan.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

public class InscribeToCourse extends JFrame {
    private EntityManagerFactory emf;
    private Checker checker;
    private Student user;
    private List<Course> courses;
    private JPanel inscribePanel;
    private JComboBox<String> coursesComboBox;
    private JButton inscribeBtn;

    public InscribeToCourse(Checker checker, EntityManagerFactory emf, User user){
        this.checker = checker;
        this.emf = emf;
        this.user = (Student) user;
        getCourses();

        add(inscribePanel);

        inscribeBtn.addActionListener(e->inscribe());
    }

    private void inscribe(){
        user.addCourse(courses.get(coursesComboBox.getSelectedIndex()));
    }
    private void getCourses(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String query = "SELECT c FROM Course c WHERE c.id IS NOT NULL";
        TypedQuery<Course> tq = em.createQuery(query,Course.class);
        et.begin();
        try{
             courses = tq.getResultList();
            courses.forEach(c->coursesComboBox.addItem(c.getCourseName()));
        }catch (NoResultException nre){
            nre.printStackTrace();
        }finally {
            em.close();
        }
    }
}
