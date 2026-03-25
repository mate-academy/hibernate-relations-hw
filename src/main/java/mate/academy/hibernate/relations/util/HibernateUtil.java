package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Country.class);
            configuration.addAnnotatedClass(Actor.class);
            configuration.addAnnotatedClass(Movie.class);

            return configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Can't create SessionFactory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
