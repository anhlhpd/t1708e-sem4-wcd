package com.t1708e.service;

import com.t1708e.entity.Student;
import com.t1708e.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentService {

    private Logger LOGGER = Logger.getLogger(Student.class.getSimpleName());

    public boolean saveOrUpdate(Student student){
        LOGGER.log(Level.INFO, String.format("Try to save student with roll number: %s", student.getRollNumber()));
        // Try with resource: sau khi try catch sẽ tự động đóng connection, không cần gọi đến session.close()
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, String.format("Save successfully!"));
            return true;
        } catch (Exception ex){
            LOGGER.log(Level.INFO, String.format("Try to save student with roll number: %s", student.getRollNumber(), ex));
            ex.printStackTrace();
        }
        return false;
    }

    public Student findById(String id){
        Student student = null;
        // Try with resource: sau khi try catch sẽ tự động đóng connection, không cần gọi đến session.close()
        try (Session session = HibernateUtil.getSession()) {
            student = session.get(Student.class, id);
//            Student student2 = session.byId(Student.class).getReference(id);
            return student;
        } catch (Exception ex){
            LOGGER.log(Level.INFO, String.format("Try to save student with roll number: %s", student.getRollNumber(), ex));
            ex.printStackTrace();
        }
        return student;
    }

    public boolean delete(Student student){
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.remove(student);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, String.format("Delete successfully!"));
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public List<Student> findAll(){
        List<Student> students = new ArrayList<>();
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return students;
    }
}
