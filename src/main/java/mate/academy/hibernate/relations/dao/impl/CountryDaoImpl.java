package mate.academy.hibernate.relations.dao.impl;

import java.util.Objects;
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
            transaction = session.getTransaction();
            transaction.begin();
            session.persist(country);
            transaction.commit();
        } catch (Exception e) {
            Objects.requireNonNull(transaction).rollback();
            throw new DataProcessingException("Can't add country to db", e);
        } finally {
            Objects.requireNonNull(session).close();
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            Country countyFromDb = session.get(Country.class, id);
            return Optional.ofNullable(countyFromDb);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get county from db by id: " + id, e);
        }
    }
}
