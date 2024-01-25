package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private final MovieDao dao;

    public MovieServiceImpl(MovieDao dao) {
        this.dao = dao;
    }

    @Override
    public Movie add(Movie movie) {
        return dao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> movie = dao.get(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new RuntimeException("No movie with id=" + id + "is found.");
        }
    }
}
