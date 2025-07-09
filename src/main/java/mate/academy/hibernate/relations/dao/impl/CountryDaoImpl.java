package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.impl.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {

        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(country);
            transaction.commit();
            return country;
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Can't add country", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Country country = session.get(Country.class, id);
            if (country == null) {
                transaction.commit();
                return Optional.empty();
            }
            return Optional.of(country);
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Can't get by id", e);
        } finally {
            session.close();
        }
    }

}
