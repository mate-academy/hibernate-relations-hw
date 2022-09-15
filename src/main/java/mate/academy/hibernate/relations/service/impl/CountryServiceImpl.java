package mate.academy.hibernate.relations.service.impl;

import mate.academy.lib.Service;
import mate.academy.lib.Injector;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
    Injector injector = Injector.getInstance("mate/academy/hibernate/relations");
    CountryDao countryDao = (CountryDao) injector.getInstance(CountryDao.class);

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).get();
    }
}
