package com.lukasbecker.thymeleaf;

import com.lukasbecker.iplan.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CoursesRepo {
EntityManagerFactory emf = Persistence.createEntityManagerFactory("IPlan");


    protected List<CourseUser> getCourses(User user) {

        List<CourseUser> courses = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        String query = "SELECT c FROM CourseUser c WHERE c.id IS NOT NULL";
        TypedQuery<CourseUser> tq = em.createQuery(query, CourseUser.class);
        et.begin();
        try {
            for(CourseUser cu : tq.getResultList()){
                if(user instanceof Student){
                    if(cu != null&&cu.getUser().getUserName().equals(user.getUserName())){
                        courses.add(cu);
                    }
                }else if(user instanceof Teacher){
                    if(cu != null&&cu.getCourse().getTeacher().getUserName().equals(user.getUserName())){
                        courses.add(cu);
                    }
                }else if (user instanceof Admin){
                    courses.add(cu);
                }

            }
            //courses = tq.getResultList();
        } catch (NoResultException nre) {
            nre.printStackTrace();
        } finally {
            em.close();
        }
        return courses ;
    }
}
