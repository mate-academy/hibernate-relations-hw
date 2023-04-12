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
            session.close();
            return country;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
            throw new DataProcessingException("Can't add Country " + country, e);
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = null;
        try {
            session = factory.openSession();
            Country country = session.get(Country.class, id);
            session.close();
            return Optional.ofNullable(country);
        } catch (Exception e) {
            session.close();
            throw new DataProcessingException("Can't get Country by id " + id, e);
        }
    }
}
