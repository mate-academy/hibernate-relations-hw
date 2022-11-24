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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cant add country to db", e);
        } finally {
            session.close();
        }

        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Optional<Country> country = null;
        try (Session session = factory.openSession()) {
            country = Optional.ofNullable(session.get(Country.class,id));

        } catch (Exception e) {
            throw new DataProcessingException("Cant get country from db by id " + id, e);

        }
        return country;
    }
}
