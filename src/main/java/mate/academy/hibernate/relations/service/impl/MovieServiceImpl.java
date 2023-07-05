package mate.academy.hibernate.relations.service.impl;

import java.util.NoSuchElementException;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Cant get movie by id" + id));
    }
}
