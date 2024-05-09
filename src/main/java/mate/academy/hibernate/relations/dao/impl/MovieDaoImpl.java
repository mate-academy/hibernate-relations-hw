package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        SessionFactory sessionFactory = factory;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new DataProcessingException("Can't add movie: " + movie, ex);
        } finally {
            session.close();
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        Optional<Movie> movie;
        try (SessionFactory sessionFactory = factory;
                Session session = sessionFactory.openSession()) {
            movie = Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception ex) {
            throw new DataProcessingException("Can't find movie with id: " + id, ex);
        }
        return movie;
    }
}
