package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private final CountryDao abstractDao;

    public CountryServiceImpl(CountryDaoImpl countryDao) {
        this.abstractDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        return abstractDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> movie = abstractDao.get(id);
        if (movie.isEmpty()) {
            throw new DataProcessingException("Country not found with id: " + id);
        }
        return movie.get();
    }
}
