package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.lib.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;
    private SessionFactory sessionFactory;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        countryDao = new CountryDaoImpl(sessionFactory);
    }

    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> countryOptional = countryDao.get(id);
        if (countryOptional.isPresent()) {
            return countryOptional.get();
        }
        throw new DataProcessingException("Not found in DB country with id = " + id);
    }
}
