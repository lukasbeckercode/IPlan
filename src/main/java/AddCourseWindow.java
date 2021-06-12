import com.lukasbecker.iplan.*;

import javax.persistence.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Window to add a course
 */
public class AddCourseWindow extends JFrame {
    private JTextField startDateTextBox;
    private JTextField endDateTextBox;
    private JComboBox<String> roomComboBox;
    private JComboBox<String> teacherComboBox;
    private JButton addCourseBtn;
    private JPanel mainPanel;
    private JTextField titleTextBox;
    private JTextField idTextBox;
    private final Checker checker;
    private final EntityManagerFactory emf;
    private List<Room> roomList;
    private List<Teacher> teacherList;

    /**
     * Constructor
     *
     * @param checker object that checks Time, Rooms and Teachers
     * @param emf     used for hibernate
     */
    public AddCourseWindow(Checker checker, EntityManagerFactory emf) {
        this.checker = checker;
        this.emf = emf;
        initialize();
    }

    /**
     * Handles window and button action events
     */
    void initialize() {
        add(mainPanel);
        roomList = getRooms();
        teacherList = getTeachers();
        addCourseBtn.addActionListener(e -> {
            try {
                addCourse();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        });
    }

    /**
     * updates which rooms exist in the database
     *
     * @return a List containing every room that exists in the Database
     */
    List<Room> getRooms() {
        EntityManager EM = emf.createEntityManager();
        String query = "SELECT r FROM Room r WHERE r.id IS NOT NULL";
        TypedQuery<Room> tq = EM.createQuery(query, Room.class);
        List<Room> rooms = null;
        try {
            rooms = tq.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            EM.close();
        }
        if (rooms != null) {
            rooms.forEach(r -> {
                roomComboBox.addItem(r.getRoomName());
            });
        }
        return rooms;
    }

    /**
     * updates the Teachers that currently exist in the Database
     *
     * @return a List containing every teacher that exists in the Database
     */
    List<Teacher> getTeachers() {
        EntityManager EM = emf.createEntityManager();
        String query = "SELECT t FROM Teacher t WHERE t.id IS NOT NULL";
        TypedQuery<Teacher> tq = EM.createQuery(query, Teacher.class);
        List<Teacher> teachers = null;
        try {
            teachers = tq.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            EM.close();
        }
        if (teachers != null)
            teachers.forEach(t -> teacherComboBox.addItem(t.getUserName()));

        return teachers;
    }

    /**
     * adds a course to the database after checking it
     *
     * @throws ParseException in theory, this would be thrown if the stored date doesn't match the expected format
     */
    void addCourse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
        Date startDate = sdf.parse(startDateTextBox.getText());
        Date endDate = sdf.parse(endDateTextBox.getText());
        String title = titleTextBox.getText();
        int id = 0;

        try {
            id = Integer.parseInt(idTextBox.getText());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        Course course = new Course(startDate, endDate, title);
        checker.addCourse(course);

        if (checker.checkRoom() != ERRORS.OK || checker.checkTeacher() != ERRORS.OK) {
            //TODO: Error message
            System.err.println("Room or time invalid");
        } else {
            //TODO: add course to database
            EntityManager EM = emf.createEntityManager();
            EntityTransaction et = null;

            try {
                et = EM.getTransaction();
                et.begin();
                Course c = new Course();
                c.setCourseID(id);
                c.setCourseName(titleTextBox.getText());
                c.setRoom(roomList.get(roomComboBox.getSelectedIndex()));
                c.setTeacher(teacherList.get(teacherComboBox.getSelectedIndex()));
                c.setStartDate(startDate);
                c.setEndDate(endDate);
                EM.persist(c);
                et.commit();
                System.out.println("Course added");
            } catch (Exception e) {
                if (et != null)
                    et.rollback();
                e.printStackTrace();
            } finally {
                EM.close();
            }
        }
    }
}
