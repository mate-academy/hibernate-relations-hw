package mate.academy.hibernate.relations.service;

import mate.academy.hibernate.relations.lib.Service;
import mate.academy.hibernate.relations.model.Movie;

@Service
public interface MovieService {
    Movie add(Movie movie);

    Movie get(Long id);
}
