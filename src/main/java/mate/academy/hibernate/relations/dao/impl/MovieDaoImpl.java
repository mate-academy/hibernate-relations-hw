package mate.academy.hibernate.relations.dao.impl;

import java.util.Optional;
import mate.academy.hibernate.relations.dao.MovieDao;
import mate.academy.hibernate.relations.model.Actor;
import mate.academy.hibernate.relations.model.Country;
import mate.academy.hibernate.relations.model.Movie;
import mate.academy.hibernate.relations.util.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MovieDaoImpl extends AbstractDao implements MovieDao {
    private final SessionFactory sessionFactory = super.factory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Movie add(Movie movie) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            for (Actor actor : movie.getActors()) {
                Actor attachedActor = session.get(Actor.class, actor.getId());
                if (attachedActor == null) {
                    Country attachedCountry
                            = session.get(Country.class, actor.getCountry().getId());
                    if (attachedCountry == null) {
                        session.persist(actor.getCountry());
                    } else {
                        actor.setCountry(attachedCountry);
                    }
                    session.persist(actor);
                } else {
                    if (actor.getCountry() != null) {
                        Country attachedCountry
                                = session.get(Country.class, actor.getCountry().getId());
                        if (attachedCountry == null) {
                            session.persist(actor.getCountry());
                        } else {
                            actor.setCountry(attachedCountry);
                        }
                    }
                    session.merge(actor);
                }
            }
            movie = session.merge(movie);
            transaction.commit();
            return movie;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while adding movie object: "
                    + movie.toString(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return Optional.ofNullable(session.get(Movie.class, id));
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
