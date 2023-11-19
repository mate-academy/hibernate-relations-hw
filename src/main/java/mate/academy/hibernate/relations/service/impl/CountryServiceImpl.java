package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private static final String CANT_FIND_BY_ID_EXCEPTION_TEXT = "Can't find the country by id";
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(()
                -> new NoSuchElementException(CANT_FIND_BY_ID_EXCEPTION_TEXT));
    }
}
