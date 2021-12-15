package mate.academy.hibernate.relations.dao.impl;

import exception.DataProcessingException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
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
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(country);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                throw new DataProcessingException(
                        "Can't add country '" + country + "' to DB", e);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Optional<Country> country;
        try (Session session = factory.openSession()) {
            country = Optional.ofNullable(session.get(Country.class, id));
        } catch (HibernateException e) {
            throw new DataProcessingException(
                    "Can't get country with id = " + id + "from DB", e);
        }
        return country;
    }
}
