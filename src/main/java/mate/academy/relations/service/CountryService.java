package mate.academy.relations.service;

import mate.academy.relations.model.Country;

public interface CountryService {
    Country add(Country country);

    Country get(Long id);
}
