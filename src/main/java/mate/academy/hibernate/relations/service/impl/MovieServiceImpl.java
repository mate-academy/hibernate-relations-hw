package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import java.util.function.Supplier;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movieOptional = movieDao.get(id);
        return movieOptional.orElseThrow((Supplier<RuntimeException>) ()
                -> new DataProcessingException("could not retrieve"
                + " movie with id: " + id, null));
    }
}
