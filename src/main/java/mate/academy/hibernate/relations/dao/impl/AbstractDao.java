package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AbstractDao<T> {
    protected final SessionFactory factory;

    public AbstractDao(SessionFactory factory) {
        this.factory = factory;
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
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add to DB: " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id, Class<T> clazz) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.find(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get info from DB by id: " + id, e);
        }
    }
}

