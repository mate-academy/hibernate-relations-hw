package mate.academy.hibernate.relations.util;

import jakarta.persistence.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        if (instance == null) {
            try {
                return new Configuration().configure().buildSessionFactory();
            } catch (Exception e) {
                throw new RuntimeException("Can't create session factory ", e);
            }
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }
}
