import com.lukasbecker.iplan.User;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;

public class Intro extends JFrame {
    private JPanel introFrame;
    private JRadioButton studentRadioBtn;
    private JRadioButton teacherRadioBtn;
    private JRadioButton adminRadioBtn;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel userNameTextBox;
    private JLabel passwordTextBox;
    private JButton continueButton;
    private final EntityManagerFactory emf;

    public Intro(EntityManagerFactory emf){
        add(introFrame);
        this.emf = emf;
        continueButton.addActionListener(e->{
            String username = userNameTextBox.getText();
            String password = passwordTextBox.getText();
            List<User> users = getUsers();
            for(User u : users){
                if(u.getUserName().equals(username)){
                    if(u.getPassword().equals(password)){
                        //TODO: Login successful
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
            return users;
    }
}
