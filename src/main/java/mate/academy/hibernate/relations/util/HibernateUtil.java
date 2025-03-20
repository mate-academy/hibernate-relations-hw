package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();

    public HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration()
                    .configure()
                    .addAnnotatedClass(mate.academy.hibernate.relations.model.Country.class)
                    .addAnnotatedClass(mate.academy.hibernate.relations.model.Actor.class)
                    .addAnnotatedClass(mate.academy.hibernate.relations.model.Movie.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
