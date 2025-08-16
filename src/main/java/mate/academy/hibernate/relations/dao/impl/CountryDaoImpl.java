package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return executeInsideTransaction(session -> {
            session.persist(country);
            return country;
        });
    }

    @Override
    public Optional<Country> get(Long id) {
        return execute(session -> Optional.ofNullable(session.get(Country.class, id)));
    }

    private <T> T executeInsideTransaction(SessionAction<T> action) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            T result = action.execute(session);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Transaction failed", e);
        }
    }

    private <T> T execute(SessionAction<T> action) {
        try (Session session = factory.openSession()) {
            return action.execute(session);
        } catch (RuntimeException e) {
            throw new DataProcessingException("Operation failed", e);
        }
    }

    @FunctionalInterface
    private interface SessionAction<T> {
        T execute(Session session);
    }
}
