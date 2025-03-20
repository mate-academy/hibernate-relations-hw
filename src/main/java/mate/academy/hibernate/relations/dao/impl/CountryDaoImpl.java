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
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            if (isCountryExists(country.getName(), session)) {
                return session.createQuery("FROM Country c WHERE c.name = :name", Country.class)
                        .setParameter("name", country.getName())
                        .uniqueResult();
            }

            session.save(country);
            transaction.commit();
            return country;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert country " + country, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private boolean isCountryExists(String countryName, Session session) {
        String hql = "FROM Country c WHERE c.name = :name";
        return session.createQuery(hql, Country.class)
                .setParameter("name", countryName)
                .uniqueResult() != null;
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get country by id " + id, e);
        }
    }
}
