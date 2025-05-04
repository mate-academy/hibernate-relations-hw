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

    public T addEntity(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add entity " + entity
                    + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }

    public Optional<T> getEntity(Long id) {
        T entity = null;
        try (Session session = factory.openSession()) {
            entity = session.get(getClassInstance(), id);
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't get entity from DB with id = " + id, e);
        }
        return Optional.ofNullable(entity);
    }

    public abstract Class<T> getClassInstance();
}
