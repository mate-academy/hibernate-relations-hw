package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao implements CountryDao {

    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Long countryId = (Long) session.save(country);
            session.getTransaction().commit();
            country.setId(countryId);
            return country;
        } catch (Exception e) {
            throw new DataProcessingException("Error while adding country to the database", e);
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        try (Session session = factory.openSession()) {
            Country country = session.get(Country.class, id);
            return Optional.ofNullable(country);
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting country from the database", e);
        }
    }
}
