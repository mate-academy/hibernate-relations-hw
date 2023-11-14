package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final CountryDao countryDao = new CountryDaoImpl(sessionFactory);

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> countryOptional = countryDao.get(id);
        return countryOptional.orElseThrow(() ->
            new EntityNotFoundException("Country with id = " + id + " no exist."));
    }
}
