package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private Session session;
    private Transaction transaction;

    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country cannot be null");
        }
        if (country.getName() == null || country.getName().isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be null or empty");
        }
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
            return country;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not add country", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID cannot be negative or zero");
        }
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Could not get country", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
