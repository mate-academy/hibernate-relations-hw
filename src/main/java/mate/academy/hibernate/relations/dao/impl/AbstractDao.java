package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public abstract class AbstractDao {
    protected final SessionFactory factory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    protected Object add(Object obj, String errorMessage) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            session.persist(obj);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(errorMessage);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return obj;
    }

    protected Object get(Long id, String objectType, String errorMessage) {
        try(Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(objectType, id));
        } catch (Exception e) {
            throw new DataProcessingException(errorMessage);
        }
    }
}
