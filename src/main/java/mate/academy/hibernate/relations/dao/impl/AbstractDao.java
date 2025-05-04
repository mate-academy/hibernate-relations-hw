package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    protected <T> T add(T entity) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add "
                    + entity.getClass().getSimpleName()
                    + " = [" + entity + "]", e);
        } finally {
            session.close();
        }
        return entity;
    }

    protected <T> Optional<T> get(Class<T> entityClass, Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.find(entityClass, id));
        } catch (RuntimeException e) {
            throw new DataProcessingException("Failed to get "
                    + entityClass.getSimpleName()
                    + " with id = [" + id + "]", e);
        }
    }
}
