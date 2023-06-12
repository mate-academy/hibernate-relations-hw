package mate.academy.relations.dao.impl;

import java.util.Optional;
import mate.academy.relations.dao.MovieDao;
import mate.academy.relations.exception.DataProcessingException;
import mate.academy.relations.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t create movie: " + movie);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (HibernateException e) {
            throw new DataProcessingException("Can`t find movie with id: " + id);
        }
    }
}
