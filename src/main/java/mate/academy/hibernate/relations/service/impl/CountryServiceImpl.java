package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private static CountryDao countryDao;

    public CountryServiceImpl(CountryDaoImpl countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> country = countryDao.get(id);
        if (country.isPresent()) {
            return country.get();
        }
        throw new NoSuchElementException("Cannot get country with id " + id);
    }
}
