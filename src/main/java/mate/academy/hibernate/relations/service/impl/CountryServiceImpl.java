package mate.academy.hibernate.relations.service.impl;

import javax.persistence.EntityNotFoundException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private final SessionFactory factory;

    public CountryServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Country add(Country country) {
        CountryDao countryDao = new CountryDaoImpl(factory);
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        CountryDao countryDao = new CountryDaoImpl(factory);
        return countryDao.get(id).orElseThrow(() -> new EntityNotFoundException("ID country " + id
                + " weren't found"));
    }
}
