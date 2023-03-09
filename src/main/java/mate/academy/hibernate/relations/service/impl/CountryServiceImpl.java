package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    public CountryServiceImpl(CountryDaoImpl countryDao) {
    }

    @Override
    public Country add(Country country) {
        return null;
    }

    @Override
    public Country get(Long id) {
        return null;
    }
}
