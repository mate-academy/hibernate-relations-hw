package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(country);
            transaction.commit();
        } catch (RuntimeException e) {
            //            throw new DataProcessingException TO BE IMPLEMENTED
        } finally {
            session.close();
        }
        return country;
    }

    @Override
    public Optional<Country> get(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Country country = null;
        try {
            country = session.get(Country.class, id);
        } catch (RuntimeException e) {
            throw new DataProcessingException("Error when adding a country", e);
        } finally {
            session.close();
        }
        return Optional.ofNullable(country);
    }
}
