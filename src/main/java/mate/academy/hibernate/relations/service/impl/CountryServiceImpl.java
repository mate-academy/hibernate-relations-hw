package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class CountryServiceImpl implements CountryService {
    public CountryServiceImpl(CountryDaoImpl countryDao) {
    }

    @Override
    public Country add(Country country) {
        CountryDao countryDao = new CountryDaoImpl(HibernateUtil.getSessionFactory());
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        CountryDao countryDao = new CountryDaoImpl(HibernateUtil.getSessionFactory());
        return countryDao.get(id).orElse(null);
    }
}
