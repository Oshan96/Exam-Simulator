package lk.ijse.examsimulator.resources;

import lk.ijse.examsimulator.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static ServiceRegistry registry;

    static {
        try {
            registry = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Lecturer.class)
                    .addAnnotatedClass(Subject.class)
                    .addAnnotatedClass(Batch.class)
                    .addAnnotatedClass(Batch_SubDetail.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(RegDetail.class)
                    .addAnnotatedClass(Exam.class)
                    .addAnnotatedClass(ExamDetail.class)
                    .addAnnotatedClass(User.class)
                    .buildMetadata().buildSessionFactory();

        } catch (Exception ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);

            StandardServiceRegistryBuilder.destroy(registry);

            throw ex;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
