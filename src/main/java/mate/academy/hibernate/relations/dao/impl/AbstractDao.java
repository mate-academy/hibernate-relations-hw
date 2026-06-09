package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.exception.DataProcessingException;
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
            return entity;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error occurred during add operation ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    protected <T> Optional<T> get(Long id, Class<T> clazz) {
        try (Session session = factory.openSession()) {
            T entity = session.get(clazz, id);
            return Optional.ofNullable(entity);
        
        } catch (Exception e) {
            throw new DataProcessingException("Error occurred during "
                    + "get operation ", e);
        }
    }
}
