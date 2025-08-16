package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
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
        if (country == null) {
            throw new DataProcessingException("The argument (country) is null");
        }
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        if (id == null) {
            throw new DataProcessingException("The argument (id) is null");
        }
        return countryDao.get(id).orElseThrow(()
                -> new EntityNotFoundException("Can't get country by id, country is null. " + id));
    }
}
