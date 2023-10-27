package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
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
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Long id = (Long) session.save(movie);
                movie.setId(id);
                transaction.commit();
                return movie;
            } catch (Exception e) {
                transaction.rollback();
                throw new DataProcessingException("Error while adding a movie", e);
            }
        } catch (Exception e) {
            throw new DataProcessingException("Error while opening a session", e);
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting a movie by ID", e);
        }
    }
}
