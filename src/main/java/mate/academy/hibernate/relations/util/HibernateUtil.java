package mate.academy.hibernate.relations.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mate-academy");
        EntityManager entityManager = emf.createEntityManager();
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        return session.getSessionFactory();
    }
}
