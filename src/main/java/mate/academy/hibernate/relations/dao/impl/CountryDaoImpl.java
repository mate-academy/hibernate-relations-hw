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
            session.save(country);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Cannot add country to database country. Country: " + country);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Country country = null;
        try (Session session = factory.openSession()) {
            country = session.get(Country.class, id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Cannot get country from table country by id: " + id);
        }
        return Optional.ofNullable(country);
    }
}
