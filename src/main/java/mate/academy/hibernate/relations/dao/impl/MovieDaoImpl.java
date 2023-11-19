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
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            throw new DataProcessingException("Error while adding movie: " + movie, e);
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Movie.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting movie by id: " + id, e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Movie", Movie.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting all movies", e);
        }
    }

    @Override
    public Movie update(Movie movie) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            throw new DataProcessingException("Error while updating movie: " + movie, e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            session.delete(movie);
            transaction.commit();
        } catch (Exception e) {
            throw new DataProcessingException("Error while deleting movie by id: " + id, e);
        }
    }
}
