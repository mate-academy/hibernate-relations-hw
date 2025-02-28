package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.util.DataProcessingException;
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
            session = super.factory.openSession();
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Can't save an actor");
        } finally {
            session.close();
        }

        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = null;
        Optional<Country> country = null;
        try {
            session = super.factory.openSession();
            country = Optional.of(session.get(Country.class, id));
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }

        return country;
    }
}
