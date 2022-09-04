package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.lib.Dao;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;

@Dao
public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        return super.add(movie);
    }

    @Override
    public Optional<Movie> get(Long id) {
        return super.get(Movie.class, id);
    }
}
