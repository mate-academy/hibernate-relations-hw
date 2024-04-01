package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    private Session session = null;
    private Transaction transaction = null;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(movie);
            transaction.commit();
            session.close();
            return movie;
        } catch (Exception e) {
            transaction.rollback();
            throw new DataProcessingException("Error adding entity: "
                    + Movie.class + " to the database", e);
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("can't get an entity: "
                    + Movie.class + " from the DB", e);
        }
    }
}
