package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.NoSuchElementException;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        return null;
    }

    @Override
    public Country get(Long id) {
        try (Session session = super.factory.openSession()){
            return session.get(Country.class, id);
        } catch (Exception e) {
            throw new NoSuchElementException("Country not found with id: " + id);
        }
    }
}
