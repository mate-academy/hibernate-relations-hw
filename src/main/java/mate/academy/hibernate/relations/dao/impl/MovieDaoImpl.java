package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
            session.persist(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not save info to the DB "
                    + "about movie: " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT m FROM Movie m "
                    + "LEFT JOIN FETCH m.actors a "
                    + "LEFT JOIN FETCH a.country "
                    + "WHERE m.id = :id";
            Query<Movie> query = session.createQuery(hql, Movie.class);
            query.setParameter("id", id);
            Movie movie = query.uniqueResult();
            return Optional.ofNullable(movie);
        } catch (Exception e) {
            throw new DataProcessingException("Не вдалося отримати фільм з id: "
                    + id, e);
        }
    }
}
