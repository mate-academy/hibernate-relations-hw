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
        Country result = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot save new country" + country, e);
        } finally {
            if (session != null) {
                session.close();
            }
            result = country;
        }
        return result;
    }

    @Override
    public Optional<Country> get(Long id) {
        Country result = null;
        Session session = null;

        try {
            session = factory.openSession();
            result = (Country) session.get(Country.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get country: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.of(result);
    }
}
