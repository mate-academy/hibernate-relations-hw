package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.ActorService;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.Hibernate;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;
    private final ActorService actorService;

    public MovieServiceImpl(MovieDao movieDao, ActorService actorService) {
        this.movieDao = movieDao;
        this.actorService = actorService;
    }

    @Override
    public Movie add(Movie movie) {
        try {
            return movieDao.add(movie);
        } catch (Exception e) {
            throw new DataProcessingException("Error while adding movie: ", e);
        }
    }

    @Override
    public Movie get(Long id) {
        Movie movie = movieDao.get(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Hibernate.initialize(movie.getActors());
        return movie;
    }
}
