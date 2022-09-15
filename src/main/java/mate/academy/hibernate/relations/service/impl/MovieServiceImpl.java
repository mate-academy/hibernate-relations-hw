package mate.academy.hibernate.relations.service.impl;

import mate.academy.lib.Service;
import mate.academy.lib.Injector;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    Injector injector = Injector.getInstance("mate/academy/hibernate/relations");
    MovieDao movieDao = (MovieDao) injector.getInstance(MovieDao.class);

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).get();
    }
}
