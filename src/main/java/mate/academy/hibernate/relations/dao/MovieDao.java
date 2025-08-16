package mate.academy.hibernate.relations.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.hibernate.relations.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    Optional<Movie> get(Long id);

    List<Movie> getAll();

    Movie update(Movie movie);

    void delete(Long id);
}
