package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    public static final String MOVIE_NULL_EXCEPTION_MESSAGE = "Movie can't be null";
    public static final String ID_NULL_EXCEPTION_MESSAGE = "Id can't be null";
    public static final String MOVIE_NOT_FOUND_MESSAGE = "Movie with id: %d was not found.";
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        if (movie == null) {
            throw new RuntimeException(MOVIE_NULL_EXCEPTION_MESSAGE);
        }

        movieDao.add(movie);

        return movie;
    }

    @Override
    public Movie get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_NULL_EXCEPTION_MESSAGE);
        }

        return movieDao.get(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                String.format(MOVIE_NOT_FOUND_MESSAGE, id)
                        )
                );
    }
}
