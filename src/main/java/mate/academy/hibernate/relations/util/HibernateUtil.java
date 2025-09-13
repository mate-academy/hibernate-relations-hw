package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = regSession();

    private HibernateUtil() {

    }

    private static SessionFactory regSession() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Error building SessionFactory. Check hibernate.cfg.xml", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
