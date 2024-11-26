package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static final String CAN_T_BUILD_SESSION_FACTORY_MSG = "Can't build session factory ";
    private static final SessionFactory session = getInitSessionFactory();

    public HibernateUtil() {
    }

    private static SessionFactory getInitSessionFactory() {

        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException(CAN_T_BUILD_SESSION_FACTORY_MSG);
        }

    }

    public static SessionFactory getSessionFactory() {
        return session;
    }
}
