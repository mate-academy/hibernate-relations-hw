package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;

public class CountryServiceImpl implements CountryService {
    private CountryService countryService = new CountryServiceImpl();

    @Override
    public Country add(Country country) {
        return countryService.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryService.get(id);
    }
}
