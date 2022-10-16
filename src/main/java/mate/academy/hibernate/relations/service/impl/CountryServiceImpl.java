package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.AbstractDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl extends AbstractService implements CountryService {
    public CountryServiceImpl(AbstractDao abstractDao) {
        super(abstractDao);
    }

    @Override
    public Country add(Country country) {
        CountryDao countryDao = (CountryDao) abstractDao;
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        CountryDao countryDao = (CountryDao) abstractDao;
        return countryDao.get(id).get();
    }
}
