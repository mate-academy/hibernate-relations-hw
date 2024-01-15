package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
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
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DataProcessingException("Failed to add " + country.toString()
                    + " to database.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = null;
        Transaction transaction = null;
        Country result = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            result = session.get(Country.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DataProcessingException("Failed to get country with ID " + id
                    + " from database.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return Optional.ofNullable(result);
    }
}
