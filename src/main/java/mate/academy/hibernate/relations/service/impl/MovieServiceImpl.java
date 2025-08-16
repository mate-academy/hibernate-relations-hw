package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.DataProcessingException;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao = new MovieDaoImpl(HibernateUtil.getSessionFactory());

    public MovieDao getMovieDao() {
        return movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() ->
                new DataProcessingException("Can`t get movie from DB"));
    }
}
