package mate.academy.relations.dao;

import java.util.Optional;
import mate.academy.relations.model.Country;

public interface CountryDao {
    Country add(Country country);

    Optional<Country> get(Long id);
}
