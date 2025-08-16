package mate.academy.hibernate.relations.service.impl;

import java.util.List;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;

    public CountryServiceImpl(SessionFactory sessionFactory) {
        this.countryDao = new CountryDaoImpl(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return countryDao.add(country);
    }

    @Override
    public Country get(Long id) {
        return countryDao.get(id)
                .orElseThrow(() -> new RuntimeException("Could not get country from DAO "
                        + "by id = " + id));
    }

    @Override
    public Country update(Country country) {
        return countryDao.update(country);
    }

    @Override
    public Country delete(Country country) {
        return countryDao.delete(country);
    }

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }
}
