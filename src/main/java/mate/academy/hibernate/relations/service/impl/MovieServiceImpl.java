package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class MovieServiceImpl implements MovieService {
    public MovieServiceImpl(MovieDaoImpl movieDao) {
    }

    @Override
    public Movie add(Movie movie) {
        MovieDao movieDao = new MovieDaoImpl(HibernateUtil.getSessionFactory());
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        MovieDao movieDao = new MovieDaoImpl(HibernateUtil.getSessionFactory());
        return movieDao.get(id).orElse(null);
    }
}
