package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        Transaction transaction = null;

        try (Session session = this.factory.openSession()) {
            transaction = session.beginTransaction();

            session.persist(country);
            transaction.commit();

            return country;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DataProcessingException("Can't add country", e);
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = this.factory.openSession()) {
            Country country = session.find(Country.class, id);

            return Optional.ofNullable(country);
        } catch (HibernateException e) {
            throw new DataProcessingException("Can't find country with id: " + id, e);
        }
    }
}
