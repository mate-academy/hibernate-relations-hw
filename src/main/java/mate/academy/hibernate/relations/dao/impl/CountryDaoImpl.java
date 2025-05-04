package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private static final String FAILED_ADDING_COUNTRY_MESSAGE
            = "Failed to add Country to the database. Country: ";
    private static final String FAILED_RETRIEVE_COUNTRY_MESSAGE
            = "Failed to retrieve Country from the database. Country id: ";

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
            session.persist(country);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(FAILED_ADDING_COUNTRY_MESSAGE + country, e);
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
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (RuntimeException e) {
            throw new DataProcessingException(FAILED_RETRIEVE_COUNTRY_MESSAGE + id, e);
        }
    }
}
