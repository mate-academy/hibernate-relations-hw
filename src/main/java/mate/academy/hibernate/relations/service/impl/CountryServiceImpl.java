package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.AbstractDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private AbstractDao abstractDao;

    public CountryServiceImpl(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public Country add(Country country) {
        return ((CountryDao) abstractDao).add(country);
    }

    @Override
    public Country get(Long id) {
        return ((CountryDao) abstractDao).get(id).get();
    }
}
