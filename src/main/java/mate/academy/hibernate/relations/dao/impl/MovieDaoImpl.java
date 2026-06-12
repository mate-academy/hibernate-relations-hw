package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        try {
            factory.inTransaction(session ->
                    session.persist(movie));
            return movie;
        } catch (Exception e) {
            throw new DataProcessingException("Can't add a new movie: " + movie, e);
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try {
            return Optional.ofNullable(factory.fromSession(session ->
                    session.find(Movie.class, id)));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a movie by ID: " + id, e);
        }
    }
}
