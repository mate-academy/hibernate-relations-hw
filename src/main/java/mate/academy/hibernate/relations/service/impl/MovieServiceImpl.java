package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        this.movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie is null!");
        }

        Movie added = movieDao.add(movie);
        return added;
    }

    @Override
    public Movie get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null!");
        }

        Optional<Movie> movie = movieDao.get(id);

        if (movie.isEmpty()) {
            throw new IllegalArgumentException("Movie not found!");
        }
        return movie.get();
    }
}
