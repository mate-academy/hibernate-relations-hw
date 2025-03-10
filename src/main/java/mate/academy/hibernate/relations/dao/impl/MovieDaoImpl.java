package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    private SessionFactory instance;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        instance = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = instance;
                Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error saving movie", e);
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (SessionFactory sessionFactory = instance;
                Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error getting movie", e);
        }
    }
}
