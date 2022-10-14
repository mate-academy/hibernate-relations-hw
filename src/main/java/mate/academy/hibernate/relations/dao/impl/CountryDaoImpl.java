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
            country.setId((Long) session.save(country));
            transaction.commit();
        } catch (Exception e) {
            transactionRollBack(transaction);
            throw new DataProcessingException("Couldn't add country '" + country + "'!", e);
        } finally {
            sessionClose(session);
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        try {
            Session session = factory.openSession();
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Couldn't get country by id = " + id);
        }
    }
}
