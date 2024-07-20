package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;

import java.util.NoSuchElementException;

public class MovieServiceImpl implements MovieService {
    MovieDao movieDao = new MovieDaoImpl(HibernateUtil.getSessionFactory());
    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(()
                -> new NoSuchElementException("Movie(ID=" + id + ") not found"));
    }
}
