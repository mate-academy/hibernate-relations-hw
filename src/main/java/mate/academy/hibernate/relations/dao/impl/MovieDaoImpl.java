package mate.academy.hibernate.relations.dao.impl;

import java.util.List;
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
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("The problem appeared during "
                    + "transaction of adding movie - " + movie + " to DB.", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        }
    }

    @Override
    public Movie update(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(movie);
            transaction.commit();
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("The problem appeared during "
                    + "transaction of updating movie - " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Movie").list();
        }
    }

    @Override
    public Movie delete(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.delete(movie);
            transaction.commit();
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("The problem appeared during "
                    + "transaction of deleting movie - " + movie + " from DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movie;
    }
}
