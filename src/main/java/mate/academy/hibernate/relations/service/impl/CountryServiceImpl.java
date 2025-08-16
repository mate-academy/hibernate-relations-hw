package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao = new CountryDaoImpl(HibernateUtil.getSessionFactory());

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> optional = countryDao.get(id);

        return optional.orElseThrow(() ->
                new EntityNotFoundException("Failed to find country with ID " + id + "."));
    }
}
