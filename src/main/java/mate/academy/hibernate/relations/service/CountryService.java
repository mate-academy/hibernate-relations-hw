package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.lib.Service;
import mate.academy.hibernate.relations.model.Country;

@Service
public interface CountryService {
    Country add(Country country);

    Country get(Long id);
}
