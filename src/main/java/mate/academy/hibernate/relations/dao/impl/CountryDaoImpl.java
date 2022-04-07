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
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();
            } else {
                throw new DataProcessingException("Can`t add country " + country + " to db", e);
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get country with id is " + id, e);
        }
    }

}
