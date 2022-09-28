package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exeptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Country> get(Long id) {
        Country country = null;
        try (Session session = factory.openSession()) {
            country = session.get(Country.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can not get actor from DB with id " + id);
        }
        return Optional.ofNullable(country);
    }

}
