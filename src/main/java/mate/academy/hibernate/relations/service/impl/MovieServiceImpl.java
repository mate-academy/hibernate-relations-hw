package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private final MovieDaoImpl movieDao;

    public MovieServiceImpl(MovieDaoImpl movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() -> new NoSuchElementException("Movie with id "
                + id + " doesn't exist"));
    }
}
