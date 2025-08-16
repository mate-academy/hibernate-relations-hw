package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        countryDao.add(country);
        return country;
    }

    @Override
    public Country get(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be positive and not null");
        }
        return countryDao.get(id)
                .orElseThrow(() -> new RuntimeException("Country with id " + id + " not found"));
    }
}
