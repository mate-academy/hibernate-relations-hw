package mate.academy.hibernate.relations.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory instance = initSessionFactory();

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return instance;
    }

    private static SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(); // hibernate.cfg.xml

        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException("db.properties not found in resources");
            }

            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load DB properties", e);
        }

        configuration.setProperty("hibernate.connection.username",
                properties.getProperty("db.username"));
        configuration.setProperty("hibernate.connection.password",
                properties.getProperty("db.password"));

        return configuration.buildSessionFactory();
    }
}

