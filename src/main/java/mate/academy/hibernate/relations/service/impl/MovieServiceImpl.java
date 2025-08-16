package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private static MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movie = movieDao.get(id);
        if (movie.isPresent()) {
            return movie.get();
        }
        throw new NoSuchElementException("Cannot get movie with id " + id);
    }
}
