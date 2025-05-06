package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;

public interface CountryService {
    Country add(Country country) throws DataProcessingException;

    Country get(Long id) throws DataProcessingException;
}
