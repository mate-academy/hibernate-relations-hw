package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie can't be null");
        }
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return movieDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't get a movie by id: " + id));
    }
}
