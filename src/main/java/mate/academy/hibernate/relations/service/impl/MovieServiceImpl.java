package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private static final String MOVIE_NOT_FOUND_MESSAGE = "Movie by id not found, id: ";
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
        return movieDao.get(id).orElseThrow(
                () -> new NoSuchElementException(MOVIE_NOT_FOUND_MESSAGE + id));
    }
}
