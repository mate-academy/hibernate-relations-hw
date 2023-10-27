package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        try {
            return movieDao.add(movie);
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Error while adding a movie", e);
        }
    }

    @Override
    public Movie get(Long id) {
        try {
            return movieDao.get(id)
                    .orElseThrow(() -> new DataProcessingException("Movie with ID "
                            + id + " not found"));
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Error while getting a movie", e);
        }
    }
}
