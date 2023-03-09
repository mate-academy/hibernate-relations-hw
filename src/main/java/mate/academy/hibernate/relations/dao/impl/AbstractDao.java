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

    public T addItem(T item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add "
                    + item.getClass().getSimpleName() + " to DB " + item, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    public Optional<T> getItem(Long id) {
        T item = null;
        try (Session session = factory.openSession()) {
            item = session.get(getClassInstance(), id);
        } catch (Exception e) {
            assert item != null;
            throw new DataProcessingException(
                    "Can't get" + item.getClass().getSimpleName() + "  from DB with id =" + id, e);
        }
        return Optional.ofNullable(item);
    }

    public abstract Class<T> getClassInstance();
}
