package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import java.util.NoSuchElementException;

public class MovieServiceImpl implements MovieService {
    private static final String EXCEPTION_MESSAGE = "Can't get movie by id=";

    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        movieDao.add(movie);
        return movie;
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(
                    () -> new NoSuchElementException(EXCEPTION_MESSAGE + id));
    }
}
