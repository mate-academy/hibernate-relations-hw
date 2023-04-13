package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private static final String EXCEPTION_MESSAGE
            = "Exception while trying to persist entity to DB.";
    private static final String EXCEPTION_GET
            = "Exception while trying to get entity from DB with id = ";

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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(EXCEPTION_MESSAGE, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Transaction transaction = null;
        Country country = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            country = session.get(Country.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(EXCEPTION_GET + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(country);
    }
}
