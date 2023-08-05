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
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = super.factory.openSession();
            transaction = currentSession.beginTransaction();
            currentSession.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movie: " + movie);
        } finally {
            currentSession.close();
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session currentSession = super.factory.openSession();) {
            Movie movie = currentSession.get(Movie.class, id);
            return Optional.ofNullable(movie);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get by id: " + id, e);
        }
    }
}
