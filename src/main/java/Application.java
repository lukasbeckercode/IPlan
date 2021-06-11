import com.lukasbecker.iplan.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;

public class Application {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("IPlan");
    static Room r = null;
    static User t = null;
    static Course c = null;
    public static void main(String[] args) {
        Checker checker = new Checker();
        Intro intro = new Intro(EMF,checker);
        intro.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        intro.pack();
        intro.setVisible(true);

        addTeacher();
      //  EMF.close();
    }
    public static void addCourse(){
        EntityManager EM = EMF.createEntityManager();
        EntityTransaction et = null;

        try {
            et = EM.getTransaction();
            et.begin();
            c = new Course();
            c.setCourseID(1);
            c.setCourseName("TestCourse");
            c.setRoom(r);
            c.setTeacher((Teacher) t);
            EM.persist(c);
            et.commit();

        }catch (Exception e){
            if(et != null)
                et.rollback();
            e.printStackTrace();
        }finally {
            EM.close();
        }
    }
    public static void addTeacher(){
        EntityManager EM = EMF.createEntityManager();
        EntityTransaction et = null;

        try {
            et = EM.getTransaction();
            et.begin();
            t = new Admin("admin","1234");
            EM.persist(t);
            et.commit();

        }catch (Exception e){
            if(et != null)
                et.rollback();
            e.printStackTrace();
        }finally {
            EM.close();
        }
    }
    public static void addRoom(){
        EntityManager EM = EMF.createEntityManager();
        EntityTransaction et = null;

        try {
            et = EM.getTransaction();
            et.begin();
            r = new Room(1,"TestRoom");
            EM.persist(r);
            et.commit();
        }catch (Exception e){
            if(et != null)
                et.rollback();
            e.printStackTrace();
        }finally {
            EM.close();
        }

    }
}
