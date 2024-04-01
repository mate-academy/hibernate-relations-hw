package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.service.CountryService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CountryServiceImpl implements CountryService {
    @Override
    public Country add(Country country) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(country);
        transaction.commit();
        session.close();
        return country;
    }

    @Override
    public Country get(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.get(Country.class, id);
    }
}
