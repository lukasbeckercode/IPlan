import com.lukasbecker.iplan.Checker;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        Checker checker = new Checker();
        AddCourseWindow addCourseWindow = new AddCourseWindow(checker);
        addCourseWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addCourseWindow.pack();
        addCourseWindow.setVisible(true);
    }
}
