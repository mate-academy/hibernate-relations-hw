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
        if (country == null) {
            throw new RuntimeException("Link to the country is null");
        }
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(country);
            transaction.commit();
            if (country.getId() == null) {
                throw new RuntimeException("Can't add this country to db - " + country);
            }
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Exception", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        if (id == null) {
            throw new RuntimeException("This 'id' is null");
        }
        Country country;
        try (Session session = factory.openSession()) {
            country = session.get(Country.class, id);
        } catch (RuntimeException ex) {
            throw new DataProcessingException("Can't get country from db by id = " + id, ex);
        }
        return Optional.ofNullable(country);
    }
}
