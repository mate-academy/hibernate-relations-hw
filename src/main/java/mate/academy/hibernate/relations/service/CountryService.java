package mate.academy.hibernate.relations.service;

import java.util.List;
import mate.academy.hibernate.relations.model.Country;

public interface CountryService {
    Country add(Country country);

    Country get(Long id);

    List<Country> getAll();

    Country update(Country country);

    void delete(Long id);
}
