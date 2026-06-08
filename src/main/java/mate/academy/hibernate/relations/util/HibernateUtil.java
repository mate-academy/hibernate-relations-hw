package mate.academy.hibernate.relations.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            try (InputStream inputStream = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Can't load properties from file", e);
            }
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(Actor.class);
            configuration.addAnnotatedClass(Country.class);
            configuration.addAnnotatedClass(Movie.class);
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
