package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        this.movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Actor can not be null!");
        }
        movieDao.add(movie);
        return movie;
    }

    @Override
    public Movie get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Movie can not be null!");
        }
        return movieDao.get(id).get();
    }
}
