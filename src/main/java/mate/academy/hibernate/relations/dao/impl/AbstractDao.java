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

    protected <T> T addEntity(T entity) {
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
            throw new DataProcessingException("Can't persist entity to DB " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    protected <T> Optional<T> getEntity(Class<T> clazz, Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.find(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't find entity of class" + clazz.getName()
                    + " by id " + id, e);
        }
    }
}
