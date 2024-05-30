package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    private static final String FAILED_ADDING_MOVIE
            = "Failed to add Movie to the database. Movie: ";
    private static final String FAILED_RETRIEVE_COUNTRY
            = "Failed to retrieve movie from the database. Movie id: ";

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
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(FAILED_ADDING_MOVIE + movie, e);
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
            Movie movie = session.get(Movie.class, id);
            if (movie != null) {
                Hibernate.initialize(movie.getActors());
            }
            return Optional.ofNullable(movie);
        } catch (RuntimeException e) {
            throw new DataProcessingException(FAILED_RETRIEVE_COUNTRY + id, e);
        }
    }
}
