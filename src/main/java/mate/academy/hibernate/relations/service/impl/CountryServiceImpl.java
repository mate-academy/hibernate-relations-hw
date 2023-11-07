package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl extends AbstractService implements CountryService {
    private static CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        initCountryDao();
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can't get country by id: " + id));
    }

    private void initCountryDao() {
        countryDao = new CountryDaoImpl(factory);
    }
}
