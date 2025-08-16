package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {

        this.countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> optionalCountry = countryDao.get(id);
        return optionalCountry
                .orElseThrow(() -> new NoSuchElementException("Country not found with ID: " + id));
    }
}
