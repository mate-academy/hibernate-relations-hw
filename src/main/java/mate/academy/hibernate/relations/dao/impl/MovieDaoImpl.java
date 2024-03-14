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
        if (movie == null) {
            throw new RuntimeException("Link to the movie is null");
        }
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(movie);
            transaction.commit();
            if (movie.getId() == null) {
                throw new RuntimeException("Can't add this movie to db -" + movie);
            }
        } catch (RuntimeException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Exception", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        if (id == null) {
            throw new RuntimeException("This 'id' is null");
        }
        Movie movie;
        try (Session session = factory.openSession()) {
            movie = session.get(Movie.class, id);
        } catch (RuntimeException ex) {
            throw new DataProcessingException("Can't get movie from db by id = " + id, ex);
        }
        return Optional.ofNullable(movie);
    }
}
