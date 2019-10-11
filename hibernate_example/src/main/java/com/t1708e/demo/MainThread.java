package com.t1708e.demo;

import com.t1708e.entity.Student;
import com.t1708e.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainThread {
    public static void main(String[] args) {

        Transaction transaction = null;

        try {
            // Tạo kết nối
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(Student.Builder.aStudent()
                    .withRollNumber("A001")
                    .withFullName("Hùng Xuân Đào")
                    .withEmail("hung@gmail.com")
                    .withPhone("0987654321")
                    .build());
            transaction.commit();
            session.close();
        } catch (Exception ex){
            if (transaction != null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
