package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.DataProcessingException;
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
        movieDao.add(movie);
        Optional<Movie> addedMovie = movieDao.get(movie.getId());
        if (addedMovie.isPresent()) {
            return addedMovie.get();
        }
        return addedMovie.orElseThrow(() -> new DataProcessingException(
                "Movie " + movie + " wasn't added to the database", null));
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movie = movieDao.get(id);
        if (movie.isPresent()) {
            return movie.get();
        }
        return movie.orElseThrow(() -> new DataProcessingException(
                "Movie with id " + id + " does not exist in database", null));
    }
}
