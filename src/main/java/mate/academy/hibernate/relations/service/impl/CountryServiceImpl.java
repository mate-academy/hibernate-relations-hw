package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private final CountryDao dao;

    public CountryServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    @Override
    public Country add(Country country) {
        return dao.add(country);
    }

    @Override
    public Country get(Long id) {
        return dao.get(id).orElseThrow(() -> new NoSuchElementException(
                String.format("Country with ID %d does not exist in the database.", id)));
    }
}
