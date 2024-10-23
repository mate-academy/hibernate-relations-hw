package mate.academy.hibernate.relations.util;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.username",
                    System.getenv("DB_USER"));
            configuration.setProperty("hibernate.connection.password",
                    System.getenv("DB_PASSWORD"));
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t create session factory", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
