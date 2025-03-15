package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

import java.util.NoSuchElementException;

public class CountryServiceImpl implements CountryService {
    CountryDao countryDao;
    @Override
    public Country add(Country country) {
        return null;
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Actor not found for id: " + id));
    }
}
