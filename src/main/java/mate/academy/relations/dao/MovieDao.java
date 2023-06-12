package mate.academy.relations.dao;

import java.util.Optional;
import mate.academy.relations.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    Optional<Movie> get(Long id);
}
