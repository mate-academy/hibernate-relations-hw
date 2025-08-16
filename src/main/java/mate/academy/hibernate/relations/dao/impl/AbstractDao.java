package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.dao.GenericDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> implements GenericDao<T> {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    @Override
    public T add(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Couldn't save "
                    + entity.getClass().getSimpleName() + ": " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
