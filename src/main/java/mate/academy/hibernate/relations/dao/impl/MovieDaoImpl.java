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
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(movie);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Can not add the movie: " + movie, e);
        } finally {
            session.close();
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = factory.openSession();
        try {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can not get a movie with id: " + id, e);
        } finally {
            session.close();
        }
    }
}
