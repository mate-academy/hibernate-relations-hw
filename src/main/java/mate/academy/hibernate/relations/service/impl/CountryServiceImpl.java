package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

import javax.persistence.EntityNotFoundException;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        countryDao.add(country);
        return country;
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(
                () -> new EntityNotFoundException("Country with id:"
                        + id + " not found"));
    }
}
