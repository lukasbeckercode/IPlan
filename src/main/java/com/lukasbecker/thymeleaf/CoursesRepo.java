package com.lukasbecker.thymeleaf;

import com.lukasbecker.iplan.Course;
import com.lukasbecker.iplan.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CoursesRepo {
EntityManagerFactory emf = Persistence.createEntityManagerFactory("IPlan");


    protected List<Course> getCourses(User user) {

        List<Course> courses = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String query = "SELECT c FROM Course c WHERE c.id IS NOT NULL";
        TypedQuery<Course> tq = em.createQuery(query, Course.class);
        et.begin();
        try {
            courses = tq.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }
        return courses ;
    }
}
