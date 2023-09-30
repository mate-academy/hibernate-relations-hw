package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return (Country) super.add(country);
    }

    @Override
    public Optional<Country> get(Long id) {
        Optional<Object> optional = super.get(id, Country.class);
        return Optional.ofNullable((Country) optional.orElse(null));
    }
}
