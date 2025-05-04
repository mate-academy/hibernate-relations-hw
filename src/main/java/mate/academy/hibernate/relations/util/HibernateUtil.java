package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Actor.class);
                configuration.addAnnotatedClass(Country.class);
                configuration.addAnnotatedClass(Movie.class);
                StandardServiceRegistryBuilder standardServiceRegistryBuilder
                        = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration
                        .buildSessionFactory(standardServiceRegistryBuilder.build());

            } catch (Exception exception) {
                throw new DataProcessingException(
                        "There is a problem with configuration in Hibernate Util",
                        exception);
            }

        }
        return sessionFactory;
    }
}
