import com.lukasbecker.iplan.Checker;
import com.lukasbecker.iplan.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.util.List;

public class Intro extends JFrame {
    private JPanel introFrame;
    private JRadioButton studentRadioBtn;
    private JRadioButton teacherRadioBtn;
    private JRadioButton adminRadioBtn;
    private JTextField userNameTextBox;
    private JTextField passwordTextBox;
    private JButton continueButton;
    private final EntityManagerFactory emf;
    private final Checker checker;

    public Intro(EntityManagerFactory emf, Checker checker){
        add(introFrame);
        this.checker = checker;
        this.emf = emf;
        continueButton.addActionListener(e->userHandler());
    }

    private void userHandler(){
        String username = userNameTextBox.getText();
        String password = passwordTextBox.getText();
        List<User> users = getUsers();
        for(User u : users){
            System.out.println(username);
            if(u.getUserName().equals(username)){
                if(u.getPassword().equals(password)){
                    if(adminRadioBtn.isSelected()){
                        AdminActionSelector adminActionSelector = new AdminActionSelector(checker,emf);
                        adminActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        adminActionSelector.pack();
                        adminActionSelector.setVisible(true);
                        this.setVisible(false);
                    }else if(teacherRadioBtn.isSelected()){
                        TeacherActionSelector teacherActionSelector = new TeacherActionSelector();
                        teacherActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        teacherActionSelector.pack();
                        teacherActionSelector.setVisible(true);
                    }else if(studentRadioBtn.isSelected()){
                        StudentActionSelector studentActionSelector = new StudentActionSelector();
                        studentActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        studentActionSelector.pack();
                        studentActionSelector.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(introFrame,"Error: No function(e.g. Student) selected",
                                "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(introFrame,"Wrong Password",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            }else{
                //TODO new user to create?
                if(adminRadioBtn.isSelected()){

                }else if(teacherRadioBtn.isSelected()){

                }else if(studentRadioBtn.isSelected()){

                }else {
                    //TODO no radioBox checked!
                }
            }

        }

    }

    private List<User> getUsers(){
        EntityManager EM = emf.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.id IS NOT NULL";
        TypedQuery<User> tq = EM.createQuery(query,User.class);
            List<User> users = null;
            try{
                users = tq.getResultList();
            }catch (NoResultException nre){
                nre.printStackTrace();
            }finally {
                EM.close();
            }
            return users;
    }
}
