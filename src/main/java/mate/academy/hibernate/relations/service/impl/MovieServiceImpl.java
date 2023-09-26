package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class MovieServiceImpl implements MovieService {
    private final SessionFactory factory ;

    public MovieServiceImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Movie add(Movie movie) {
        MovieDao movieDao = new MovieDaoImpl(factory);
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        MovieDao movieDao = new MovieDaoImpl(factory);
        return movieDao.get(id).get();
    }
}
