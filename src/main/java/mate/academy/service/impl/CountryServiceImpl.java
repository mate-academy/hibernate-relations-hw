package mate.academy.service.impl;

import java.util.Optional;
import mate.academy.dao.CountryDao;
import mate.academy.dao.impl.CountryDaoImpl;
import mate.academy.exceptions.NoValueForParameterFound;
import mate.academy.model.Country;
import mate.academy.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> countryOptional = countryDao.get(id);
        if (countryOptional.isPresent()) {
            return countryOptional.get();
        } else {
            throw new NoValueForParameterFound("Can't find a country with id " + id);
        }
    }
}
