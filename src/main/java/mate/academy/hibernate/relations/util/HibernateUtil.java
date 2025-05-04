package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory factory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new DataProcessingException("Can't create session factory ", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
