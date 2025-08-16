package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private SessionFactory sessionFactory;
    private CountryDao countryDao;

    public CountryServiceImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found country with id: " + id));
    }
}
