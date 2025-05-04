package mate.academy.hibernate.relations.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.CountryDao;
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
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Long countryId = (Long) session.save(country);
            transaction.commit();
            country.setId(countryId);
            return country;
        } catch (Exception e) {
            throw new DataProcessingException("Error while adding country to the database", e);
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Country.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting country from the database", e);
        }
    }

    @Override
    public List<Country> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Country", Country.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting countries from the database", e);
        }
    }

    @Override
    public Country update(Country country) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Country existingCountry = session.get(Country.class, country.getId());
            if (existingCountry != null) {
                session.update(country);
            }
            transaction.commit();
            return country;
        } catch (Exception e) {
            throw new DataProcessingException("Error while updating country in the database", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Country countryToDelete = session.get(Country.class, id);
            if (countryToDelete != null) {
                session.delete(countryToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new DataProcessingException("Error while deleting country from the database", e);
        }
    }
}
