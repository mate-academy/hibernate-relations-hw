package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory instance = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Actor.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(Movie.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }
}
