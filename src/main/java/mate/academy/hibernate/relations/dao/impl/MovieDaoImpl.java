package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return addEntity(movie);
    }

    @Override
    public Optional<Movie> get(Long id) {
        return getEntity(id);
    }

    @Override
    public Class<Movie> getClassInstance() {
        return Movie.class;
    }
}
