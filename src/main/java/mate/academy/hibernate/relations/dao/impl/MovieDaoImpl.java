package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.impl.DataProcessingException;
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
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("can't add movie", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Movie movie = session.get(Movie.class, id);
            if (movie == null) {
                transaction.commit();
                return Optional.empty();
            }
            transaction.commit();
            return Optional.of(movie);
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Can't get by id", e);
        } finally {
            session.close();
        }
    }
}
