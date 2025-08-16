package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDaoImpl countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        return new CountryDaoImpl(HibernateUtil.getSessionFactory()).add(country);
    }

    @Override
    public Country get(Long id) {
        CountryDao countryDao = new CountryDaoImpl(HibernateUtil.getSessionFactory());
        return countryDao.get(id).orElseThrow(() ->
                new DataProcessingException("Country with id " + id + " not found"));
    }
}
