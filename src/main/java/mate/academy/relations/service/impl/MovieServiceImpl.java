package mate.academy.relations.service.impl;

import mate.academy.relations.dao.MovieDao;
import mate.academy.relations.dao.impl.MovieDaoImpl;
import mate.academy.relations.model.Movie;
import mate.academy.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow();
    }
}
