package mate.academy.hibernate.relations.dao.impl;

import java.util.Objects;
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
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.persist(movie);
            transaction.commit();
        } catch (Exception e) {
            Objects.requireNonNull(transaction).rollback();
            throw new DataProcessingException("Can't add movie to dataBase", e);
        } finally {
            Objects.requireNonNull(session).close();
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            Movie movieFromDb = session.get(Movie.class, id);
            return Optional.ofNullable(movieFromDb);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie from dataBase with id: " + id, e);
        }
    }
}
