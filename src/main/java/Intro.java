import com.lukasbecker.iplan.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

/**
 * First Login Screen every user sees in startup of the app
 */
public class Intro extends JFrame {
    private JPanel introFrame;
    private JRadioButton studentRadioBtn;
    private JRadioButton teacherRadioBtn;
    private JRadioButton adminRadioBtn;
    private JTextField userNameTextBox;
    private JTextField passwordTextBox;
    private JButton continueButton;
    private JButton createUserBtn;
    private JButton exitBtn;
    private final EntityManagerFactory emf;
    private final Checker checker;

    /**
     * Constructor
     *
     * @param emf     used for hibernate
     * @param checker for checking time, rooms and teachers later
     */
    public Intro(EntityManagerFactory emf, Checker checker) {
        add(introFrame);
        this.checker = checker;
        this.emf = emf;
        continueButton.addActionListener(e -> userHandler());
        createUserBtn.addActionListener(e -> addUser());
        exitBtn.addActionListener(e -> {
            emf.close();
            System.exit(0);
        });
    }

    /**
     * adds a user to the database
     */
    private void addUser() {
        User u = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        if (adminRadioBtn.isSelected()) {
            u = new Admin(userNameTextBox.getText(), passwordTextBox.getText());
        } else if (teacherRadioBtn.isSelected()) {
            u = new Teacher(userNameTextBox.getText(), passwordTextBox.getText());
        } else if (studentRadioBtn.isSelected()) {
            u = new Student(userNameTextBox.getText(), passwordTextBox.getText());
        } else {
            //TODO no radioBox checked!
            return;
        }
        et.begin();
        em.persist(u);
        et.commit();
    }

    /**
     * checks credentials of a user
     */
    private void userHandler() {
        String username = userNameTextBox.getText();
        String password = passwordTextBox.getText();
        List<User> users = getUsers();
        for (User u : users) {
            System.out.println(username);
            if (u.getUserName().equals(username)) {
                if (u.getPassword().equals(password)) {
                    if (adminRadioBtn.isSelected()) {
                        AdminActionSelector adminActionSelector = new AdminActionSelector(checker, emf, u);
                        adminActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        adminActionSelector.pack();
                        adminActionSelector.setVisible(true);
                        // this.setVisible(false);
                    } else if (teacherRadioBtn.isSelected()) {
                        TeacherActionSelector teacherActionSelector = new TeacherActionSelector(u);
                        teacherActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        teacherActionSelector.pack();
                        teacherActionSelector.setVisible(true);
                    } else if (studentRadioBtn.isSelected()) {
                        StudentActionSelector studentActionSelector = new StudentActionSelector(checker, emf, u);
                        studentActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        studentActionSelector.pack();
                        studentActionSelector.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(introFrame, "Error: No function(e.g. Student) selected",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(introFrame, "Wrong Password",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            } else {
                //TODO new user to create?

            }

        }

    }

    /**
     * gets every existing user from the database
     *
     * @return a list of every user in the database
     */
    private List<User> getUsers() {
        EntityManager EM = emf.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.id IS NOT NULL";
        TypedQuery<User> tq = EM.createQuery(query, User.class);
        List<User> users = null;
        try {
            users = tq.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            EM.close();
        }
        return users;
    }
}
