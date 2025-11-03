package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(org.hibernate.SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        Transaction tx = null;
        Session session = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();
            session.save(movie);
            tx.commit();
            return movie;
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception ignored) {
                    // ignored intentionally
                }
            }
            throw new DataProcessingException("Can't insert Movie " + movie, e);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ignored) {
                    // ignored intentionally
                }
            }
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            Query<Movie> query = session.createQuery(
                    "select m from Movie m left join fetch m.actors where m.id = :id", Movie.class);
            query.setParameter("id", id);
            Movie movie = query.uniqueResult();
            return Optional.ofNullable(movie);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Movie by id " + id, e);
        }
    }
}
