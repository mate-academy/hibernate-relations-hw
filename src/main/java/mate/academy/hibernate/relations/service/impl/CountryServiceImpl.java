package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private SessionFactory factory;

    public CountryServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Country add(Country country) {
        return new CountryDaoImpl(factory).add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> countryGetOptional = new CountryDaoImpl(factory).get(id);
        if (countryGetOptional.isEmpty()) {
            return null;
        }
        Country countryProcessed = countryGetOptional.get();
        return countryProcessed;
    }
}
