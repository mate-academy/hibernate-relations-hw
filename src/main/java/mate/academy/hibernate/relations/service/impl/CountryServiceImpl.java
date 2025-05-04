package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        this.countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country is null!");
        }

        Country added = countryDao.add(country);
        return added;
    }

    @Override
    public Country get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null!");
        }

        Optional<Country> country = countryDao.get(id);

        if (country.isEmpty()) {
            throw new IllegalArgumentException("Country not found!");
        }
        return country.get();
    }
}
