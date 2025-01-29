package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.model.Country;

import java.util.Optional;

public interface CountryService {
    Country add(Country country);

    Optional<Country> get(Long id);
}
