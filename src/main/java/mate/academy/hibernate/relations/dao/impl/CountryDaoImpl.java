package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Optional;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private static final String ADD_EXCEPTION_MESSAGE = "Failed to add movie to db";
    private static final String FIND_EXCEPTION_MESSAGE = "Failed to find object by id";

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
            }
            throw new DataProcessingException(ADD_EXCEPTION_MESSAGE, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = null;
        try {
            session = factory.openSession();
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (HibernateException e) {
            throw new DataProcessingException(FIND_EXCEPTION_MESSAGE + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
