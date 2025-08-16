package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Movie;

public interface MovieService {
    Movie add(Movie movie) throws DataProcessingException;

    Movie get(Long id);
}
