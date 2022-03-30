package mate.academy.hibernate.relations.dao;

import java.util.Optional;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;

public interface CountryDao {
    Country add(Country country) throws DataProcessingException;

    Optional<Country> get(Long id);
}
