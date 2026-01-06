package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private final MovieDao md;

    public MovieServiceImpl(MovieDao md) {
        this.md = md;
    }

    @Override
    public Movie add(Movie movie) {
        return md.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return md.get(id).orElseThrow(
                () -> new RuntimeException("Cant get movie by id: " + id));
    }
}
