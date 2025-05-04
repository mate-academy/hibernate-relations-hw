package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exeption.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {

    public static final String CAN_T_ADD_MOVIE_TO_DB_MSG = "Can't add movie to db: ";
    public static final String CAN_T_GET_MOVIE_BY_ID_MSG = "Can't get Movie by id: ";

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
            session.persist(movie);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(CAN_T_ADD_MOVIE_TO_DB_MSG + movie, e);
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
                return Optional.of(movie);
            } else {
                return Optional.empty();
            }
        } catch (RuntimeException e) {
            throw new DataProcessingException(CAN_T_GET_MOVIE_BY_ID_MSG + id, e);
        }
    }
}
