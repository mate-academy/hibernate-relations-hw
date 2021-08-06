package mate.academy.hibernate.relations.dao.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.NoSuchElementException;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return null;
    }

    @Override
    public Movie get(Long id) {
        try (Session session = super.factory.openSession()){
            return session.get(Movie.class, id);
        } catch (Exception e) {
            throw new NoSuchElementException("Movie not found with id: " + id);
        }
    }
}
