package mate.academy.hibernate.relations.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao = new MovieDaoImpl(HibernateUtil.getSessionFactory());

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> optional = movieDao.get(id);

        return optional.orElseThrow(() ->
                new EntityNotFoundException("Failed to find movie with ID " + id + "."));
    }
}
