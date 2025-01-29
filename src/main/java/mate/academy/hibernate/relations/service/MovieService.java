package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.model.Movie;

import java.util.Optional;

public interface MovieService {
    Movie add(Movie movie);

    Optional<Movie> get(Long id);
}
