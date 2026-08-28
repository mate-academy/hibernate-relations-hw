package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    private CountryDao countryDao;

    @Override
    public Country add(Country country) {
        if (country == null) {
            throw new NullPointerException("Can't find a country");
        }
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Can't find id");
        }
        return countryDao.get(id).orElseThrow(
                () -> new IllegalArgumentException("Can't find id"));
    }
}
