package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private final CountryDao cd;

    public CountryServiceImpl(CountryDao cd) {
        this.cd = cd;
    }

    @Override
    public Country add(Country country) {
        return cd.add(country);
    }

    @Override
    public Country get(Long id) {
        return cd.get(id).orElseThrow(
                () -> new RuntimeException("Cant get country by id" + id));
    }
}
