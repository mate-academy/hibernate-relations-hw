package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private SessionFactory instance = null;

    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        instance = sessionFactory;
    }

    @Override
    public Country add(Country country) {
        SessionFactory sessionFactory = instance;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
            return country;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error adding country", e);
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        SessionFactory sessionFactory = instance;
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error getting country", e);
        }
    }
}
