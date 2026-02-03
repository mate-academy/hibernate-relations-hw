package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be negative");
        }
        return movieDao.get(id).orElseThrow(
                () -> new RuntimeException("Movie Not Found, id = " + id)
        );
    }
}
