package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
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
    public Country add(Country country) {
        countryDao.add(country);
        Optional<Country> addedCountry = countryDao.get(country.getId());
        if (addedCountry.isPresent()) {
            return addedCountry.get();
        }
        return addedCountry.orElseThrow(() -> new DataProcessingException(
                "Country " + country + "wasn't added to the database", null));
    }

    @Override
    public Country get(Long id) {
        Optional<Country> country = countryDao.get(id);
        if (country.isPresent()) {
            return country.get();
        }
        return country.orElseThrow(() -> new DataProcessingException(
                "Country with id " + id + " does not exist in database", null));
    }
}
