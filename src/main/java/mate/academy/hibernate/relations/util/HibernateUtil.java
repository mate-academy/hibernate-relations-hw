package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;

public class HibernateUtil {
    private static SessionFactory instance = initSessionFactory();
    
    public HibernateUtil() {
    }
    
    private static SessionFactory initSessionFactory() {
        return null;
    }
    
    public static SessionFactory getSessionFactory() {
        return instance;
    }
}
