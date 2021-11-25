package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl extends AbstractService implements MovieService {
    private final MovieDao movieDao = new MovieDaoImpl(factory);

    public MovieServiceImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        movieDao.add(movie);
        return movie;
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(()
                -> new NoSuchElementException("No movie by id: " + id));
    }
}
