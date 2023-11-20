package mate.academy.hibernate.relations.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
import mate.academy.hibernate.relations.dao.MovieDao;
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
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while adding movie: " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = factory.openSession();
        try {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting movie by id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        Session session = factory.openSession();
        try {
            return session.createQuery("FROM Movie", Movie.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting all movies", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Movie update(Movie movie) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while updating movie: " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Movie movie = session.get(Movie.class, id);
            if (movie != null) {
                session.delete(movie);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while deleting movie by id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
