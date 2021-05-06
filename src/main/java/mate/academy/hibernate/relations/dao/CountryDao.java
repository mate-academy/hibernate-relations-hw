package mate.academy.hibernate.relations.dao;

import mate.academy.hibernate.relations.model.Country;

public interface CountryDao {
    Country add(Country country);

    Country get(Long id);
}
