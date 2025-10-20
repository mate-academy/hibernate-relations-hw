package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    protected <T> T add(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add object: " + t + "in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return t;
    }

    protected <T> Optional<T> get(Class<T> clazz,Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.find(clazz, id));
        } catch (RuntimeException e) {
            throw new DataProcessingException("Can`t get object with class: "
                    + clazz + " with id: " + id, e);
        }
    }
}
