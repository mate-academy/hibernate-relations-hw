package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exeptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final CountryDao countryDao = new CountryDaoImpl(sessionFactory);

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> countryOptional = countryDao.get(id);
        if (countryOptional.isEmpty()) {
            throw new DataProcessingException("Country with id = " + id + " no exist.");
        }
        return countryOptional.get();
    }
}
