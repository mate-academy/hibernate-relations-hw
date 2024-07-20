package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        String errorMessage = "Cannot add the country: " + country.toString();
        return (Country) super.add(country, errorMessage);
    }

    @Override
    public Optional<Country> get(Long id) {
        String errormesage = "Cannot get country(ID=" + id + ") from DB";
        return (Optional<Country>) super.get(id, Country.class.toString(), errormesage);
    }
}
