package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class CountryServiceImpl implements CountryService {
    private final CountryDao countryDao = new CountryDaoImpl(HibernateUtil.getSessionFactory());

    @Override
    public Country add(Country country) {
        try {
            return countryDao.add(country);
        } catch (RuntimeException e) {
            throw new DataProcessingException("Error adding country", e);
        }
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id).orElseThrow(
                () -> new DataProcessingException("Cannot get a country by id: " + id, null));
    }
}
