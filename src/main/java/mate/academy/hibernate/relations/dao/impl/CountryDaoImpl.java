package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        try {
            factory.inTransaction(session ->
                    session.persist(country));
            return country;
        } catch (Exception e) {
            throw new DataProcessingException("Can't add a new country: " + country, e);
        }
    }

    @Override
    public Optional<Country> get(Long id) {
        try {
            return Optional.ofNullable(factory.fromSession(session ->
                    session.find(Country.class, id)));
        } catch (Exception e) {
            throw new DataProcessingException("Can't add a country by ID: " + id, e);
        }

    }
}
