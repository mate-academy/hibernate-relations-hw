package mate.academy.hibernate.relations.dao;

import mate.academy.hibernate.relations.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    Movie get(Long id);
}
