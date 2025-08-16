package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.lib.Inject;
import mate.academy.hibernate.relations.lib.Service;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
    @Inject
    private CountryDao countryDao;

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(() ->
                new DataProcessingException("Can't get country with ID: " + id));
    }
}
