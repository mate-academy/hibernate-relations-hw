package mate.academy.hibernate.relations.service.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import mate.academy.hibernate.relations.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final MovieDao MOVIE_DAO = new MovieDaoImpl(sessionFactory);

    public MovieServiceImpl(SessionFactory sessionFactory) {
        MovieServiceImpl.sessionFactory = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        return MOVIE_DAO.add(movie);
    }

    @Override
    public Movie get(Long id) {
        Optional<Movie> optionalMovie = MOVIE_DAO.get(id);
        return optionalMovie.orElse(null);
    }
}
