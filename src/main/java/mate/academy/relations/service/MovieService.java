package mate.academy.relations.service;

import mate.academy.relations.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    Movie get(Long id);
}
