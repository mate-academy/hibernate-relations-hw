package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao = new MovieDaoImpl(HibernateUtil.getSessionFactory());

    @Override
    public Movie add(Movie movie) {
        if (movie == null) {
            throw new DataProcessingException("ERROR: The movie is null!");
        }
        movieDao.add(movie);
        return movie;
    }

    @Override
    public Movie get(Long id) {
        if (id == null) {
            throw new DataProcessingException("ERROR: The id is null!");
        }
        return movieDao.get(id).orElseThrow();
    }
}
