package mate.academy.hibernate.relations.util;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed " + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

