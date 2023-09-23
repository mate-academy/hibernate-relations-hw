package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        factory.inTransaction(session -> session.persist(movie));
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = factory.openSession();
        Optional<Movie> movieFromDb = Optional.ofNullable(session.get(Movie.class, id));
        session.close();
        return movieFromDb;
    }
}
