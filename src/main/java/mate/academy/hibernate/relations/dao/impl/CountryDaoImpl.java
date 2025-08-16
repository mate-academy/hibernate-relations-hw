package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = super.factory.openSession();
        Country country = session.get(Country.class, id);
        session.close();
        return Optional.ofNullable(country);
    }
}
