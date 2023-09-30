package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return (Movie) super.add(movie);
    }

    @Override
    public Optional<Movie> get(Long id) {
        Optional<Object> optional = super.get(id, Movie.class);
        return Optional.ofNullable((Movie) optional.orElse(null));
    }
}
