package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private SessionFactory sessionFactory;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        MovieDao movieDao = new MovieDaoImpl(sessionFactory);
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be negative");
        }
        MovieDao movieDao = new MovieDaoImpl(sessionFactory);
        return movieDao.get(id)
                .orElseThrow(() -> new DataProcessingException(
                        "Movie not found, id: " + id));
    }
}
