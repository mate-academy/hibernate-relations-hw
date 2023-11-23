package mate.academy.hibernate.relations.service;

import java.util.List;
import mate.academy.hibernate.relations.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    Movie get(Long id);

    List<Movie> getAll();

    Movie update(Movie movie);

    void delete(Long id);
}
