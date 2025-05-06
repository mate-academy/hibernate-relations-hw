package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) throws DataProcessingException {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) throws DataProcessingException {
        return countryDao.get(id).orElseThrow(() ->
             new DataProcessingException("Failed to get the Country object with ID: " + id, null));
    }
}
