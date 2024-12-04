package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private static MovieDao movieDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        try {
            return movieDao.add(movie);
        } catch (DataProcessingException e) {
            return null;
        }
    }

    @Override
    public Movie get(Long id) {
        try {
            return movieDao.get(id).orElse(null);
        } catch (DataProcessingException e) {
            return null;
        }
    }
}
