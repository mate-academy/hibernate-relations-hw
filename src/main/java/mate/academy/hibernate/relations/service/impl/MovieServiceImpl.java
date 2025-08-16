package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie must not be null");
        }
        movieDao.add(movie);
        return movie;
    }

    @Override
    public Movie get(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be positive and not null");
        }
        return movieDao.get(id)
                .orElseThrow(() -> new RuntimeException("Movie with id " + id + " not found"));
    }
}
