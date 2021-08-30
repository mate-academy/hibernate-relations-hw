package mate.academy.hibernate.relations.service;

import java.util.Optional;
import mate.academy.hibernate.relations.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    Optional<Movie> get(Long id);
}
