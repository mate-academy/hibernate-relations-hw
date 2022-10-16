package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.AbstractDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl implements MovieService {
    private AbstractDao abstractDao;

    public MovieServiceImpl(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public Movie add(Movie movie) {
        return ((MovieDao) abstractDao).add(movie);
    }

    @Override
    public Movie get(Long id) {
        return ((MovieDao) abstractDao).get(id).get();
    }
}
