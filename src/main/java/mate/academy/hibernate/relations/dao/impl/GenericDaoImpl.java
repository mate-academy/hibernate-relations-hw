package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class GenericDaoImpl<T> extends AbstractDao implements GenericDao<T>{
    protected GenericDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T add(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add entity " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return t;
    }

    @Override
    public abstract Optional<T> get(Long id);
}
