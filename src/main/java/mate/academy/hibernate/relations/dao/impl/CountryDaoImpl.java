package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.HibernateError;
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
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(country);
            transaction.commit();
            return country;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add this country: " + country);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            Country country = session.get(Country.class, id);
            return Optional.ofNullable(country);
        } catch (HibernateError e) {
            throw new DataProcessingException("Can't find actor by this id: " + id);
        }
    }
}
