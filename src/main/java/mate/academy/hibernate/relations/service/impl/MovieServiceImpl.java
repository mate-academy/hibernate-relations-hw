package mate.academy.hibernate.relations.service.impl;

import java.util.List;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        this.movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id)
                .orElseThrow(() -> new RuntimeException("Could not get movie from DAO "
                        + "by id = " + id));
    }

    @Override
    public Movie update(Movie movie) {
        return movieDao.update(movie);
    }

    @Override
    public Movie delete(Movie movie) {
        return movieDao.delete(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
