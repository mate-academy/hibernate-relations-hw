package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        if (movie == null || movie.getTitle() == null) {
            throw new DataProcessingException("Movie cannot be null");
        }
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao
                .get(id)
                .orElseThrow(
                        () -> new DataProcessingException("Cannot find movie with id= " + id));
    }
}
