package mate.academy.hibernate.relations.dao.impl;

import java.io.Serializable;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        Country savedCountry;
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            savedCountry = (Country) session.save(country);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Couldn't save country: "
                    + country, hibernateException);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return savedCountry;
    }

    @Override
    public Optional<Country> get(Long id) {
        Country country;
        try (Session session = factory.openSession()) {
            country = session.get(Country.class, id);
        }
        return Optional.ofNullable(country);
    }
}
