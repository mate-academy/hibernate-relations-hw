package mate.academy.hibernate.relations.service;

import java.util.Optional;
import mate.academy.hibernate.relations.model.Country;

public interface CountryService {
    Country add(Country country);

    Optional<Country> get(Long id);
}
