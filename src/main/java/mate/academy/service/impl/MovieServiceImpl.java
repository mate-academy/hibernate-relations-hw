package mate.academy.service.impl;

import java.util.Optional;
import mate.academy.dao.MovieDao;
import mate.academy.dao.impl.MovieDaoImpl;
import mate.academy.exceptions.NoValueForParameterFound;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movieOptional = movieDao.get(id);
        if (movieOptional.isPresent()) {
            return movieOptional.get();
        } else {
            throw new NoValueForParameterFound("Can't find a movie with id " + id);
        }
    }
}
