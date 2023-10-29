package mate.academy.hibernate.relations.dao.impl;

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
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can't add %s entity to DB".formatted(entity.getClass().getSimpleName()), e
            );
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }
}
