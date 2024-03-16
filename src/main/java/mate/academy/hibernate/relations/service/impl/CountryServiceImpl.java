package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import java.util.function.Supplier;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
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
        Optional<Country> countryOptional = countryDao.get(id);
        return countryOptional.orElseThrow((Supplier<RuntimeException>) ()
                -> new DataProcessingException("could not retrieve"
                + " country with id: " + id, null));
    }
}
