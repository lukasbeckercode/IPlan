import javax.swing.*;
import com.lukasbecker.iplan.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCourseWindow extends JFrame {
    private JTextField startDateTextBox;
    private JTextField endDateTextBox;
    private JComboBox roomComboBox;
    private JComboBox teacherComboBox;
    private JButton addCourseBtn;
    private JPanel mainPanel;
    private JTextField titleTextBox;
    private JButton addRoomBtn;
    private final Checker checker;

    public AddCourseWindow(Checker checker)  {
        this.checker = checker;
        initialize();
    }

    void initialize(){
        add(mainPanel);

        addCourseBtn.addActionListener(e->{
            try {
                addCourse();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });
    }

    void addCourse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        Date startDate = sdf.parse(startDateTextBox.getText());
        Date endDate = sdf.parse(endDateTextBox.getText());
        String title = titleTextBox.getText();
        Course course = new Course(startDate,endDate,title);
        checker.addCourse(course);

       if(checker.checkRoom() != ERRORS.OK || checker.checkTeacher()!=ERRORS.OK){
           //TODO: Error message
       }else{
           //TODO: add course to database
       }
    }
}
