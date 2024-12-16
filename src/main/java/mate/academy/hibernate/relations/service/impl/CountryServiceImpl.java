package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.exceptions.DataNotFoundException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl extends AbstractService implements CountryService {
    private final CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        super();
        this.countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        validateService.validateNotNull(country, "Country can't be null");
        countryDao.add(country);
        return country;
    }

    @Override
    public Country get(Long id) {
        validateService.validateNotNull(id, "Id can't be null.");
        return countryDao.get(id).orElseThrow(() -> new DataNotFoundException("Country with id "
                + id + " not found"));
    }
}
