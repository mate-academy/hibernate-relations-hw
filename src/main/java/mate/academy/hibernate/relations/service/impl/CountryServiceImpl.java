package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CountryServiceImpl implements CountryService {
    private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);
    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country add(Country country) {
        try {
            return countryDao.add(country);
        } catch (Exception e) {
            logger.error("Can't add country: " + country, e);
            throw new DataProcessingException("Can't add country: " + country, e);
        }
    }

    @Override
    public Country get(Long id) {
        try {
            Optional<Country> countryOptional = countryDao.get(id);
            if (countryOptional.isEmpty()) {
                throw new DataProcessingException("Country not found with id: " + id);
            }
            return countryOptional.get();
        } catch (Exception e) {
            logger.error("Can't get country by id: " + id, e);
            throw new DataProcessingException("Can't get country by id: " + id, e);
        }
    }
}
