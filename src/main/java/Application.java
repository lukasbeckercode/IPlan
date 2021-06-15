import com.lukasbecker.UI.Intro;
import com.lukasbecker.iplan.*;
import com.lukasbecker.thymeleaf.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;

public class Application {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("IPlan");
    public static void main(String[] args) {
        Checker checker = new Checker();
        Thread ui = new Thread(()->{
            Intro intro = new Intro(EMF,checker);
            intro.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            intro.pack();
            intro.setVisible(true);
        });

        ui.start();

        Thread thymeleaf = new Thread(()-> app.run(args));
        thymeleaf.start();
       // EMF.close();
    }

}
