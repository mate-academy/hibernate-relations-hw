package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public static final String ADD_EXCEPTION_MESSAGE = "Unable to add country to DB : ";
    public static final String GET_EXCEPTION_MESSAGE = "Unable to get country with id: ";

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
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(ADD_EXCEPTION_MESSAGE + country, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return country;

    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            Country country = session.get(Country.class, id);
            session.close();
            return Optional.ofNullable(country);
        } catch (RuntimeException e) {
            throw new DataProcessingException(GET_EXCEPTION_MESSAGE + id, e);
        }
    }
}
