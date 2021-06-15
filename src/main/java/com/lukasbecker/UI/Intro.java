package com.lukasbecker.UI;

import com.lukasbecker.iplan.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

/**
 * Intro <br />
 * First Login Screen every user sees in startup of the app
 * @author Lukas Becker
 * Last Change: 15/06/2021, 22:52
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
        createUserBtn.addActionListener(e ->{
            if(!userExists(userNameTextBox.getText())){
                int okCancel=  JOptionPane.showConfirmDialog(introFrame,"Add user?",
                        "Confirm...",JOptionPane.OK_CANCEL_OPTION);

                if(okCancel == JOptionPane.OK_OPTION){
                    addUser();
                }
            }

        });
        exitBtn.addActionListener(e -> {
            emf.close();
            System.exit(0);
        });
    }

    /**
     * adds a user to the database
     */
    private void addUser() {
        User u;
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        if (adminRadioBtn.isSelected()) {
            u = new Admin(userNameTextBox.getText(), passwordTextBox.getText());
        } else if (teacherRadioBtn.isSelected()) {
            u = new Teacher(userNameTextBox.getText(), passwordTextBox.getText());
        } else if (studentRadioBtn.isSelected()) {
            u = new Student(userNameTextBox.getText(), passwordTextBox.getText());
        } else {
            JOptionPane.showMessageDialog(introFrame,"Error-No user function (e.g. Student) selected",
                    "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        et.begin();
        em.persist(u);
        et.commit();
    }


    /**
     * checks if a user exists or not
     * @param userName the username to search for
     * @return true if found, false if not
     */
    public boolean userExists(String userName){
        for(User user:getUsers()){
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    /**
     * checks credentials of a user
     */
    private void userHandler() {
        String username = userNameTextBox.getText();
        String password = passwordTextBox.getText();
        boolean noUserFound = true;
        List<User> users = getUsers();
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                noUserFound = false;
                if (u.getPassword().equals(password)) {
                    User.setCurrentUser(u);
                    if (adminRadioBtn.isSelected()) {
                        AdminActionSelector adminActionSelector = new AdminActionSelector(checker, emf);
                        adminActionSelector.pack();
                        adminActionSelector.setVisible(true);
                        adminActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        this.setVisible(false);
                    } else if (teacherRadioBtn.isSelected()) {
                        TeacherActionSelector teacherActionSelector = new TeacherActionSelector(emf);
                        teacherActionSelector.pack();
                        teacherActionSelector.setVisible(true);
                        teacherActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        this.setVisible(false);
                    } else if (studentRadioBtn.isSelected()) {
                        StudentActionSelector studentActionSelector = new StudentActionSelector(checker, emf);
                        studentActionSelector.pack();
                        studentActionSelector.setVisible(true);
                        studentActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(introFrame, "Error: No function(e.g. Student) selected",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        User.setCurrentUser(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(introFrame, "Wrong Password",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }

        }

        if(noUserFound){
            JOptionPane.showMessageDialog(introFrame,"Wrong Username",
                    "Error",JOptionPane.ERROR_MESSAGE);
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
