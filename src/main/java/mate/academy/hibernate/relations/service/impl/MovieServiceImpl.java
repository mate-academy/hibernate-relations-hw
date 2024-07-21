package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
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
            throw new DataProcessingException("The argument (movie) is null");
        }
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        if (id == null) {
            throw new DataProcessingException("The argument (id) is null");
        }
        return movieDao.get(id).orElseThrow(()
                -> new EntityNotFoundException("Can't get movie by id, movie is null. " + id));
    }
}
