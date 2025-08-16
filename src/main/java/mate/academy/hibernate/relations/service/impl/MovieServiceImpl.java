package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private SessionFactory sessionFactory;
    private MovieDao movieDao;

    public MovieServiceImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found movie with id: " + id));
    }
}
