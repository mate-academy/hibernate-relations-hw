package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exceptions.DataNotFoundException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl extends AbstractService implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        super();
        this.movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        validateService.validateNotNull(movie, "Movie can't be null");
        movieDao.add(movie);
        return movie;
    }

    @Override
    public Movie get(Long id) {
        validateService.validateNotNull(id, "Id can't be null.");
        return movieDao.get(id).orElseThrow(() -> new DataNotFoundException("Movie with id "
                + id + " not found"));
    }
}
