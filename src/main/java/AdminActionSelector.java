import com.lukasbecker.iplan.Checker;
import com.lukasbecker.iplan.User;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;

public class AdminActionSelector extends JFrame{
    private JPanel adminSelectorFrame;
    private JButton addRoomBtn;
    private JButton addACourseButton;
    private JButton button3;
    private Checker checker;
    private EntityManagerFactory emf;
    public AdminActionSelector(Checker checker, EntityManagerFactory emf, User u){
        this.emf = emf;
        add(adminSelectorFrame);
        this.checker = checker;
        addRoomBtn.addActionListener(e->{
            AddRoomWindow addRoomWindow = new AddRoomWindow(emf);
            addRoomWindow.pack();
            addRoomWindow.setVisible(true);
        });

        addACourseButton.addActionListener(e->{
            AddCourseWindow addCourseWindow = new AddCourseWindow(checker,emf);
            addCourseWindow.pack();
            addCourseWindow.setVisible(true);
        });
    }
}
