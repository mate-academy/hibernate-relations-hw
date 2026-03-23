package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.DataProcessingException;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        if (movie == null) {
            throw new DataProcessingException("Movie can't be null");
        }
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        if (id == null) {
            throw new DataProcessingException("Id can't be null");
        }
        return movieDao.get(id).orElseThrow(
                () -> new DataProcessingException("Can't find movie by id " + id));
    }
}
