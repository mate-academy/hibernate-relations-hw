package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final MovieDao movieDao = new MovieDaoImpl(sessionFactory);

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movieOptional = movieDao.get(id);
        return movieOptional.orElseThrow(() ->
            new EntityNotFoundException("Movie with id = " + id + " no exist."));
    }
}
