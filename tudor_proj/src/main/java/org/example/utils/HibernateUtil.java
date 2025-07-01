package org.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.domain.Ticket;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            configuration.addAnnotatedClass(Ticket.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
