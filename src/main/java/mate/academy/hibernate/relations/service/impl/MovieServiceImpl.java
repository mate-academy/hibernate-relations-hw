package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private SessionFactory factory;

    public MovieServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Movie add(Movie movie) {
        return new MovieDaoImpl(factory).add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movieGetOptional = new MovieDaoImpl(factory).get(id);
        if (movieGetOptional.isEmpty()) {
            return null;
        }
        Movie movieProcessed = movieGetOptional.get();
        return movieProcessed;
    }
}
