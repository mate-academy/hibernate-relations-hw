package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    protected T add(T instance, Class<T> clazz) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(instance);
            transaction.commit();
            return instance;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                                              + clazz.getSimpleName() + " entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    protected Optional<T> get(Long id, Class<T> clazz) {
        try (Session session = factory.openSession()) {
            T t = session.get(clazz, id);
            return Optional.ofNullable(t);
        }
    }
}
