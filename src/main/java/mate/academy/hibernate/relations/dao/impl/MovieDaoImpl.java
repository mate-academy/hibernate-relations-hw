package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Movie;
import org.hibernate.SessionFactory;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        String errorMessage = "Cannot add the movie: " + movie.toString();
        return (Movie) super.add(movie, errorMessage);
    }

    @Override
    public Optional<Movie> get(Long id) {
        String errormesage = "Cannot get movie(ID=" + id + ") from DB";
        return (Optional<Movie>) super.get(id, Movie.class.toString(), errormesage);
    }
}
