package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.DataProcessingException;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        try {
            return movieDao.add(movie);
        } catch (Exception e) {
            throw new DataProcessingException("Error adding movie: " + movie, e);
        }
    }

    @Override
    public Movie get(Long id) {
        try {
            return movieDao.get(id)
                    .orElseThrow(()
                            -> new DataProcessingException("Movie not found with id: " + id));
        } catch (Exception e) {
            throw new DataProcessingException("Error fetching movie with id " + id, e);
        }
    }
}
