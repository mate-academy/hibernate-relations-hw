package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        try {
            return movieDao.add(movie);
        } catch (Exception e) {
            logger.error("Can't add movie: " + movie, e);
            throw new DataProcessingException("Can't add movie: " + movie, e);
        }
    }

    @Override
    public Movie get(Long id) {
        try {
            Optional<Movie> movieOptional = movieDao.get(id);
            if (movieOptional.isEmpty()) {
                throw new DataProcessingException("Movie not found with id: " + id);
            }
            return movieOptional.get();
        } catch (Exception e) {
            logger.error("Can't get movie by id: " + id, e);
            throw new DataProcessingException("Can't get movie by id: " + id, e);
        }
    }
}
