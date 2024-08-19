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
        if (this.factory == null) {
            throw new DataProcessingException("Factory not initialized");
        }
        Session session = this.factory.openSession();
        if (session == null) {
            throw new DataProcessingException("Session not initialized");
        }
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(movie);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Error in adding actor", e);
        } finally {
            session.close();
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = this.factory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error getting country", e);
        }
    }
}
