package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryServiceImpl implements CountryService {
    private final SessionFactory factory;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
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
            return country;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error adding Country: " + country, e);
        } finally {
            assert session != null;
            session.close();
        }
    }

    @Override
    public Country get(Long id) {
        try (Session session = factory.openSession()) {
            return session.get(Country.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving Country with ID " + id, e);
        }
    }
}
