package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private SessionFactory sessionFactory;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country add(Country country) {
        if (country.getName() == null || country.getName().isEmpty()) {
            throw new IllegalArgumentException("Country name cannot be empty");
        }

        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }

        CountryDao countryDao = new CountryDaoImpl(sessionFactory);
        return countryDao.get(id)
                .orElseThrow(() -> new DataProcessingException(
                        "Can not find actor with id: " + id));
    }
}
