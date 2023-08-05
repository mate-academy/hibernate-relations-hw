package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
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
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = super.factory.openSession();
            transaction = currentSession.beginTransaction();
            currentSession.save(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add country: " + country,e);
        } finally {
            currentSession.close();
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session currentSession = super.factory.openSession();) {
            Country country = currentSession.get(Country.class, id);
            return Optional.ofNullable(country);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get by id: " + id, e);
        }
    }
}
