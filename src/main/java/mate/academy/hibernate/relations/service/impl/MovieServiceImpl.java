package mate.academy.hibernate.relations.service.impl;

import java.util.List;
import mate.academy.hibernate.relations.dao.ActorDao;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.dao.impl.ActorDaoImpl;
import mate.academy.hibernate.relations.dao.impl.MovieDaoImpl;
import mate.academy.hibernate.relations.exception.DataProcessingException;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.service.MovieService;
import org.hibernate.SessionFactory;

public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;
    private final ActorDao actorDao;

    public MovieServiceImpl(SessionFactory sessionFactory) {
        this.movieDao = new MovieDaoImpl(sessionFactory);
        this.actorDao = new ActorDaoImpl(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        List<Actor> actors = movie.getActors();
        if (actors != null && actors.size() > 0) {
            for (Actor actor : actors) {
                if (actor.getId() == null) {
                    actorDao.add(actor);
                }
            }
        }

        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() -> {
            return new DataProcessingException("There is no such a movie with id: " + id);
        });
    }
}
