package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(mate.academy.hibernate.relations.model.Movie.class);
        configuration.addAnnotatedClass(mate.academy.hibernate.relations.model.Actor.class);
        configuration.addAnnotatedClass(mate.academy.hibernate.relations.model.Country.class);
        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }
}
