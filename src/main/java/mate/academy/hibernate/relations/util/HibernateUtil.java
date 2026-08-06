package mate.academy.hibernate.relations.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration().configure();

        if (configuration.getProperty("hibernate.connection.url") == null) {
            Properties properties = loadDatabaseProperties();

            configuration.setProperty(
                    "hibernate.connection.url",
                    properties.getProperty("db.url")
            );
            configuration.setProperty(
                    "hibernate.connection.username",
                    properties.getProperty("db.username")
            );
            configuration.setProperty(
                    "hibernate.connection.password",
                    properties.getProperty("db.password")
            );
        }

        return configuration.buildSessionFactory();
    }

    private static Properties loadDatabaseProperties() {
        Properties properties = new Properties();

        try (InputStream input = HibernateUtil.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Can't find db.properties");
            }
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Can't load db.properties", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
