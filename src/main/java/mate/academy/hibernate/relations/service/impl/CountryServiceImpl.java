package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private CountryDaoImpl countryDao;

    public CountryServiceImpl(CountryDaoImpl countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(() ->
                new RuntimeException("Country with id " + id + " not found"));
    }
}
