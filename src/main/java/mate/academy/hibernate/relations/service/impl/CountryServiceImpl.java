package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        if (country.getName() == null || country.getName().isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be empty");
        }
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        return countryDao.get(id).orElseThrow(
                () -> new RuntimeException("Country Not Found, id = " + id)
        );
    }
}
