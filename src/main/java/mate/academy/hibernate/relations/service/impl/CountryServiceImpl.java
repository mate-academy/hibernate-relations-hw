package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    public static final String COUNTRY_NULL_EXCEPTION_MESSAGE = "Country can't be null";
    public static final String ID_NULL_EXCEPTION_MESSAGE = "Id can't be null";
    public static final String COUNTRY_NOT_FOUND_MESSAGE = "Country with id: %d was not found.";
    private CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        if (country == null) {
            throw new RuntimeException(COUNTRY_NULL_EXCEPTION_MESSAGE);
        }
        countryDao.add(country);
        return country;
    }

    @Override
    public Country get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_NULL_EXCEPTION_MESSAGE);
        }

        return countryDao.get(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format(COUNTRY_NOT_FOUND_MESSAGE, id)
                        )
                );
    }
}
