package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.Dao;
import mate.academy.hibernate.relations.dao.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> implements Dao<T> {
    protected final SessionFactory factory;
    protected final Class<T> clazz;

    protected AbstractDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.factory = sessionFactory;
        this.clazz = clazz;
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
            throw new DataProcessingException("Can't insert entity "
                    + clazz.getSimpleName() + " " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id) {
        try (Session session = factory.openSession()) {

            T entity = session.get(clazz, id);
            return Optional.ofNullable(entity);

        } catch (Exception e) {
            throw new DataProcessingException("Can't find entity by id " + id, e);
        }
    }
}
