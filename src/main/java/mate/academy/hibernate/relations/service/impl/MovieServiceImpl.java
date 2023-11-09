package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exeptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class MovieServiceImpl implements MovieService {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final MovieDao movieDao = new MovieDaoImpl(sessionFactory);

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movieOptional = movieDao.get(id);
        if (movieOptional.isEmpty()) {
            throw new DataProcessingException("Movie with id = " + id + " no exist.");
        }
        return movieOptional.get();
    }
}
