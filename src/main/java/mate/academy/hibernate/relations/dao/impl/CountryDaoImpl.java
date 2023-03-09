package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Country> getClassInstance() {
        return Country.class;
    }

    @Override
    public Country add(Country country) {
        return addItem(country);
    }

    @Override
    public Optional<Country> get(Long id) {
        return getItem(id);
    }
}
