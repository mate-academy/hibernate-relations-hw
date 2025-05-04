package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
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
            throw new DataProcessingException(
                    "Unexpected error while adding country: " + country, e);
        }
    }

    @Override
    public Country get(Long id) {
        try {
            Optional<Country> countryOptional = countryDao.get(id);
            if (countryOptional.isEmpty()) {
                throw new DataProcessingException("Country not found with id: " + id);
            }
            return countryOptional.get();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get country by id: " + id, e);
        }
    }
}
