package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.DataProcessingException;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        try {
            return countryDao.add(country);
        } catch (Exception e) {
            throw new DataProcessingException("Error adding country: " + country, e);
        }
    }

    @Override
    public Country get(Long id) {
        try {
            return countryDao.get(id)
                    .orElseThrow(()
                            -> new DataProcessingException("Country not found with id: " + id));
        } catch (Exception e) {
            throw new DataProcessingException("Error fetching country with id " + id, e);
        }
    }
}
