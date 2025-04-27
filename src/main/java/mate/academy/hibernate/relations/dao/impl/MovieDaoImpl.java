package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Optional;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.persist(movie);
            tx.commit();
            return movie;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new DataProcessingException("Can't add Movie " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = null;
        try {
            session = factory.openSession();
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Movie by id " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
