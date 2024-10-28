package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private final MovieDao abstractDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.abstractDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return abstractDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movie = abstractDao.get(id);
        if (movie.isEmpty()) {
            throw new DataProcessingException("Movie not found with id: " + id);
        }
        return movie.get();
    }
}
