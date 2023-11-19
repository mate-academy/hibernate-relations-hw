package mate.academy.hibernate.relations.dao.impl;

import java.util.List;
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
        return null;
    }

    @Override
    public Optional<Country> get(Long id) {
        return null;
    }

    @Override
    public List<Country> getAll() {
        return null;
    }

    @Override
    public Country update(Country country) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
