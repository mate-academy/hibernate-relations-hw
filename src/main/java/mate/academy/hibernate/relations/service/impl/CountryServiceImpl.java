package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        try {
            return countryDao.add(country);
        } catch (Exception e) {
            throw new DataProcessingException("Failed to add to the Db actor: " + country);
        }
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id)
                .orElseThrow(() -> new DataProcessingException("Actor not found with id: " + id));
    }
}
