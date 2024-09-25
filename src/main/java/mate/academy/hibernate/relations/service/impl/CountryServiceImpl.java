package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
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
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> country = countryDao.get(id);
        if (country.isEmpty()) {
            throw new EntityNotFoundException("Country with id " + id + " not found");
        }
        return country.get();
    }
}
