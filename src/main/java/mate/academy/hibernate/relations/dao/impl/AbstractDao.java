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

    public T add(T entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can't add entity: " + entity, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id, Class<T> clazz) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't get entity by id: " + id, e);
        }
    }
}
