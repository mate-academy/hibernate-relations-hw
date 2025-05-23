package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exceptions.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao = new MovieDaoImpl(HibernateUtil.getSessionFactory());

    @Override
    public Movie add(Movie movie) {
        try {
            return movieDao.add(movie);
        } catch (RuntimeException e) {
            throw new DataProcessingException("Error adding movie", e);
        }
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(
                () -> new DataProcessingException("Cannot get a movie by id: " + id, null));
    }
}
