import com.lukasbecker.iplan.Checker;
import com.lukasbecker.iplan.User;

import javax.persistence.*;
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
        continueButton.addActionListener(e->{
            String username = userNameTextBox.getText();
            String password = passwordTextBox.getText();
            List<User> users = getUsers();
            for(User u : users){
                System.out.println(username);
                if(u.getUserName().equals(username)){
                    if(u.getPassword().equals(password)){
                        //TODO: Login successful
                        if(adminRadioBtn.isSelected()){
                            AdminActionSelector adminActionSelector = new AdminActionSelector(checker);
                            adminActionSelector.setDefaultCloseOperation(EXIT_ON_CLOSE);
                            adminActionSelector.pack();
                            System.out.println("A window should open now...");
                            adminActionSelector.setVisible(true);
                            this.setVisible(false);
                        }else if(teacherRadioBtn.isSelected()){

                        }else if(studentRadioBtn.isSelected()){

                        }else {
                            //TODO no radioBox checked!
                        }
                    }else{
                        //TODO: Login failed
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

        });
    }

    List<User> getUsers(){
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
            users.forEach(u-> System.out.println(u.getUserName()));
            return users;
    }
}
