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
    public Country add(Country country) throws DataProcessingException {
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
            throw new DataProcessingException("Can't add actor to the DB: " + country, e);
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) throws DataProcessingException {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.find(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Failed to save the Country "
                + "object with ID: " + id, e);
        }
    }
}
