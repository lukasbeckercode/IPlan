import com.lukasbecker.iplan.Checker;

import javax.swing.*;

public class AdminActionSelector extends JFrame{
    private JPanel adminSelectorFrame;
    private JButton addRoomBtn;
    private JButton addACourseButton;
    private JButton button3;
    private Checker checker;
    public AdminActionSelector(Checker checker){
        add(adminSelectorFrame);
        this.checker = checker;
        addRoomBtn.addActionListener(e->{
            AddRoomWindow addRoomWindow = new AddRoomWindow();
            addRoomWindow.pack();
            addRoomWindow.setVisible(true);
        });

        addACourseButton.addActionListener(e->{
            AddCourseWindow addCourseWindow = new AddCourseWindow(checker);
            addCourseWindow.pack();
            addCourseWindow.setVisible(true);
        });
    }
}
