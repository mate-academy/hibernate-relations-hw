package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.DataProcessingException;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;

import java.util.Optional;

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
        Optional<Country> optionalCountry = countryDao.get(id);
        return optionalCountry.orElse(null);
    }
}
