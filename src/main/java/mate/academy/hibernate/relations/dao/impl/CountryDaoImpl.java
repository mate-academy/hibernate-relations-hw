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
            throw new DataProcessingException("Error when adding a country: " + country, e);
        } finally {
            session.close();
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = null;
        Country country = null;
        try {
            session = factory.openSession();
            country = session.get(Country.class, id);
        } catch (RuntimeException e) {
            throw new DataProcessingException("Error when getting a country: " + country, e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(country);
    }
}
