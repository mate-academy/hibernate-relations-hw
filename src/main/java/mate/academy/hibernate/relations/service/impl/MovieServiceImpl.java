package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private MovieService movieService;

    public MovieServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public Movie add(Movie movie) {
        return movieService.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieService.get(id);
    }
}
