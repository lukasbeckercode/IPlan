import com.lukasbecker.iplan.Room;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;

public class AddRoomWindow extends JFrame {
    private JPanel addRoomPanel;
    private JTextField roomNameTextBox;
    private JTextField roomNumberTextBox;
    private JButton createRoomBtn;
    private EntityManagerFactory emf;

    public AddRoomWindow(EntityManagerFactory emf){
        this.emf= emf;
        add(addRoomPanel);
        createRoomBtn.addActionListener(e->addRoom());
    }

    private void addRoom(){
        String name = roomNameTextBox.getText();
        int nr = 0;
        try{
            nr = Integer.parseInt(roomNumberTextBox.getText());
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
            JOptionPane.showMessageDialog(addRoomPanel,"Error: Room number must ba a number!",
                    "Room Number Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        et = em.getTransaction();
        et.begin();
        Room r = new Room(nr,name);
        em.persist(r);
        et.commit();
    }
}
