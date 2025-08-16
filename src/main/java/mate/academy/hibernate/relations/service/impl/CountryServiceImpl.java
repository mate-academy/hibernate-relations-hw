package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl extends AbstractService implements CountryService {
    private final CountryDao countryDao = new CountryDaoImpl(factory);

    public CountryServiceImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        countryDao.add(country);
        return country;
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(()
                -> new NoSuchElementException("No country by id: " + id));
    }
}
