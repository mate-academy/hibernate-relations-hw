package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.lib.Inject;
import mate.academy.hibernate.relations.lib.Service;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> optionalMovie = movieDao.get(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        }
        throw new RuntimeException("Don't exist Movie with id " + id + " in DB");
    }
}
