package mate.academy.relations.service.impl;

import mate.academy.relations.dao.CountryDao;
import mate.academy.relations.dao.impl.CountryDaoImpl;
import mate.academy.relations.model.Country;
import mate.academy.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow();
    }
}
