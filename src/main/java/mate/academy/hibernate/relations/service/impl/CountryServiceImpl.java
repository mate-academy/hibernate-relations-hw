package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.dao.impl.CountryDaoImpl;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class CountryServiceImpl implements CountryService {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final CountryDao COUNTRY_DAO = new CountryDaoImpl(sessionFactory);

    public CountryServiceImpl(SessionFactory sessionFactory) {
        CountryServiceImpl.sessionFactory = sessionFactory;
    }

    @Override
    public Country add(Country country) {
        return COUNTRY_DAO.add(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> optionalCountry = COUNTRY_DAO.get(id);
        return optionalCountry.orElse(null);
    }
}
