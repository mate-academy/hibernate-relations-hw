package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    //I`m not sure here I can use abstract method, cause
    //in this case I will not be able to specify concrete
    //entity name in exception
    protected <T> T add(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot add entity of " + entity.getClass(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
