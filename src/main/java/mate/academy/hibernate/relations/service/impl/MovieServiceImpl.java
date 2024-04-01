package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieServiceImpl implements MovieService {
    @Override
    public Movie add(Movie movie) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(movie);
        transaction.commit();
        session.close();
        return movie;
    }

    @Override
    public Movie get(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.get(Movie.class, id);
    }
}
