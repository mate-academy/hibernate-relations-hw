package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.AbstractDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

public class MovieServiceImpl extends AbstractService implements MovieService {
    public MovieServiceImpl(AbstractDao abstractDao) {
        super(abstractDao);
    }

    @Override
    public Movie add(Movie movie) {
        MovieDao movieDao = (MovieDao) abstractDao;
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        MovieDao movieDao = (MovieDao) abstractDao;
        return movieDao.get(id).get();
    }
}
