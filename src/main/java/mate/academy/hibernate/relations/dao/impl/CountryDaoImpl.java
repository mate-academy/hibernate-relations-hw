package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exeption.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private static final String NOT_ADD_THE_COUNTRY_TO_DB = "Can not add the country to DB";
    private static final String NOT_GET_THE_COUNTRY_BY_ID = "Unable to retrieve an country by ID";

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
            throw new DataProcessingException(NOT_ADD_THE_COUNTRY_TO_DB + country, e);
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
            throw new DataProcessingException(NOT_GET_THE_COUNTRY_BY_ID + id, e);
        }
    }
}
