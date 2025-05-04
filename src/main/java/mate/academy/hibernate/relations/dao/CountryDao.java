package mate.academy.hibernate.relations.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.model.Country;

public interface CountryDao {
    Country add(Country country);

    Optional<Country> get(Long id);

    List<Country> getAll();

    Country update(Country country);

    void delete(Long id);
}
