package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.CountryDao;
import mate.academy.hibernate.relations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryDaoImpl extends AbstractDao implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Country add(Country country) {
        Session session = this.factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(country);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            session.close();
            throw new DataProcessingException();
        }
        session.close();
        return country;

    }

    @Override
    public Optional<Country> get(Long id) {
        Session session = this.factory.openSession();
        Optional<Country> answer = Optional.ofNullable(session.get(Country.class,id));
        session.close();
        return answer;
    }
}

