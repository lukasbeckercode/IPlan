import com.lukasbecker.iplan.Checker;
import com.lukasbecker.iplan.User;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;

/**
 * Lets an Admin choose what he wants to do
 */
public class AdminActionSelector extends JFrame {
    private JPanel adminSelectorFrame;
    private JButton addRoomBtn;
    private JButton addACourseButton;
    private JButton button3;

    /**
     * Constructor
     *
     * @param checker used to check courses,dates, teachers and rooms
     * @param emf     used for hibernate
     * @param u       the current user
     */
    public AdminActionSelector(Checker checker, EntityManagerFactory emf, User u) {
        add(adminSelectorFrame);
        addRoomBtn.addActionListener(e -> {
            AddRoomWindow addRoomWindow = new AddRoomWindow(emf);
            addRoomWindow.pack();
            addRoomWindow.setVisible(true);
        });

        addACourseButton.addActionListener(e -> {
            AddCourseWindow addCourseWindow = new AddCourseWindow(checker, emf);
            addCourseWindow.pack();
            addCourseWindow.setVisible(true);
        });
    }
}
