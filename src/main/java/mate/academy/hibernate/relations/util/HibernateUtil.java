package mate.academy.hibernate.relations.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    //    public static SessionFactory getSessionFactory() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mate-academy");
//        EntityManager entityManager = emf.createEntityManager();
//        Session session = entityManager.unwrap(org.hibernate.Session.class);
//        return session.getSessionFactory();
//    }
    //
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
