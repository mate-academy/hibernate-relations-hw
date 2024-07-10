package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private static CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        countryDao = new CountryDaoImpl(HibernateUtil.getInstance());
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(
                () -> new RuntimeException("DB don't have country with such id = " + id));
    }
}
