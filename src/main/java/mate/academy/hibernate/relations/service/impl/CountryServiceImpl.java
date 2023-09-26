package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private final SessionFactory sessionFactory;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country add(Country country) {
        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        return countryDao.get(id).get();
    }
}
